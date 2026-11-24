package com.vankorno.vankornocompose.fileOps.media.pic

import android.database.sqlite.SQLiteDatabase
import com.vankorno.vankornocompose._entities.pic.TT_Pic
import com.vankorno.vankornocompose._entities.pic.TT_PicUsage
import com.vankorno.vankornocompose.db.miscTable.MiscTableOps.getDaysSinceFirstAppLaunch
import com.vankorno.vankornocompose.fileOps.LibFileOps
import com.vankorno.vankornodb.api.DbRuntime.dbh
import com.vankorno.vankornodb.delete.deleteRows
import com.vankorno.vankornodb.get.getColLongs
import com.vankorno.vankornodb.get.getEntities
import com.vankorno.vankornodb.get.getStrings
import com.vankorno.vankornodb.get.hasRows
import com.vankorno.vankornodb.get.id.getAllIDs
import com.vankorno.vankornodb.get.id.getStrById
import com.vankorno.vankornodb.get.id.hasId
import com.vankorno.vankornodb.misc.suppressValGetterErrorLog
import com.vankorno.vankornohelpers.dLog
import com.vankorno.vankornohelpers.getCurrTime
import com.vankorno.vankornohelpers.values.LibConstants.MillisInDay

object LibPicCleanup {
    private const val TAG = "LibPicCleanup"
    
    fun singleSubjPicCleanup(                                            subjTableName: String,
                                                                                subjId: Long,
    ) = dbh.write("singleSubjPicCleanup") { db ->
        singleSubjPicCleanup(db, subjTableName, subjId)
    }
    
    fun singleSubjPicCleanup(                                                   db: SQLiteDatabase,
                                                                     subjTableName: String,
                                                                            subjId: Long,
    ) {
        val picIDs = db.getColLongs(TT_PicUsage.col { ObjId },
            where = {
                SubjId equal subjId
                SubjTable equal subjTableName
            }
        )
        if (picIDs.isEmpty()) return //\/\/\/\/\/\
        
        for (picId in picIDs) {
            if (isPicBroken(db, picId)) {
                cleanupPic(db)
                break //\/\/\
            }
        }
    }
    
    fun isPicBroken(                                                             picId: Long
    ): Boolean = dbh.read(true, "isPicBroken") { db ->
        isPicBroken(db, picId)
    }
    
    fun isPicBroken(                                                            db: SQLiteDatabase,
                                                                             picId: Long,
    ): Boolean {
        suppressValGetterErrorLog = true
        val path = db.getStrById(picId, TT_Pic.col{ Path })
        suppressValGetterErrorLog = false
        
        if (path.isBlank()) return true //\/\/\/\/\/\
        
        val fileMissing = !LibFileOps.fileExists(path)
        
        return fileMissing
    }
    
    
    fun getAllPicPaths(): List<String> = dbh.read(emptyList(), "getAllPicPaths") {
        db -> getAllPicPaths(db)
    }
    fun getAllPicPaths(                                                         db: SQLiteDatabase
    ): List<String> = db.getStrings(TT_Pic.columns { Path then PreviewPath })
        .flatten()
        .filter { it.isNotBlank() }
    
    
    fun handlePicCleanup(                                               daysTillDelete: Int = 10
    ) = dbh.write("handlePicCleanup") { db ->
        handlePicCleanup(db, daysTillDelete)
    }
    fun handlePicCleanup(                                                       db: SQLiteDatabase,
                                                                    daysTillDelete: Int = 10,
    ) {
        val freshApp = getDaysSinceFirstAppLaunch(db) < 30
        if (!freshApp)
            cleanupPic(db, daysTillDelete)
    }
    
    
    fun cleanupPic(                                                     daysTillDelete: Int = 10
    ) = dbh.write("cleanupPic") { db ->
        cleanupPic(db, daysTillDelete)
    }
    fun cleanupPic(                                                             db: SQLiteDatabase,
                                                                    daysTillDelete: Int = 10,
    ) {
        // region LOG
            dLog(TAG, "cleanupPic()")
        // endregion
        deleteBrokenPics(db)
        deleteUnusedPicsInDb(db, daysTillDelete)
        deleteOrphanPicFiles(db)
    }
    
    
    
    fun deleteBrokenPics() = dbh.write("deleteBrokenPics") { db -> deleteBrokenPics(db) }
    
    fun deleteBrokenPics(                                                       db: SQLiteDatabase
    ) {
        // region LOG
            dLog(TAG, "deleteBrokenPics()")
        // endregion
        deletePathLosers(db)
        deleteFileLosers(db)
        deleteOrphanUsages(db)
    }
    
    
    private fun deletePathLosers(db: SQLiteDatabase) = db.deleteRows(TT_Pic) { Path equal "" }
    
    
    
    fun deleteOrphanUsages() = dbh.write("deleteOrphanUsages") { db -> deleteOrphanUsages(db) }
    
    fun deleteOrphanUsages(                                                     db: SQLiteDatabase
    ) {
        // TODO Maybe use more low-level db stuff instead
        val picIDs = db.getColLongs(TT_PicUsage.col{ ObjId }).distinct()
        
        for (picId in picIDs) {
            val exists = db.hasId(picId, TT_Pic)
            if (!exists) {
                db.deleteRows(TT_PicUsage) { ObjId equal picId }
            }
        }
    }
    
    
    
    private fun deleteFileLosers(                                               db: SQLiteDatabase
    ) {
        // region LOG
            dLog(TAG, "deleteFileLosers()")
        // endregion
        val fileLosers = getFileLoserIDs(db)
        if (fileLosers.isEmpty()) return //\/\/\/\/\/\
        
        db.deleteRows(TT_Pic) { Id equalAny fileLosers }
    }
    
    
    
    fun deleteOrphanPicFiles() = dbh.write("deleteOrphanPicFiles") { db -> deleteOrphanPicFiles(db) }
    
    fun deleteOrphanPicFiles(                                                   db: SQLiteDatabase
    ) {
        // region LOG
            dLog(TAG, "deleteOrphanPicFiles()")
        // endregion
        val fileNames = LibFileOps.getAllFileNames(PicDirName)
        
        val dbFileNames = getAllPicPaths(db)
                            .map { it.substringAfterLast('/') }
                            .toSet()
        
        for (fileName in fileNames) {
            if (fileName !in dbFileNames) {
                LibFileOps.deleteFile(PicDirName, fileName)
            }
        }
    }
    
    
    
    /** PicEntt rows that are in use, but don't have the actual files.
     */
    private fun getFileLoserIDs(                                                db: SQLiteDatabase
    ): List<Long> {
        val objToCheck = db.getEntities(TT_Pic)
        
        if (objToCheck.isEmpty()) {
            // region LOG
                dLog(TAG, "getFileLoserIDs(): No file loss detected.")
            // endregion
            return emptyList() //\/\/\/\/\/\
        }
        
        val existingFileNames = LibFileOps.getAllFileNames(PicDirName).toSet()
        
        return buildList {
            for (obj in objToCheck) {
                val fileName = obj.path.substringAfterLast('/')
                
                if (fileName !in existingFileNames) {
                    add(obj.id)
                }
            }
        }
    }
    
    
    fun deleteUnusedPicsInDb(                                           daysTillDelete: Int
    ) = dbh.write("DeleteUnusedPics") { db ->
        deleteUnusedPicsInDb(db, daysTillDelete)
    }
    
    fun deleteUnusedPicsInDb(                                                   db: SQLiteDatabase,
                                                                    daysTillDelete: Int,
    ) {
        val millisTillDelete = daysTillDelete * MillisInDay
        val delLine = getCurrTime() - millisTillDelete
        
        val ids = db.getAllIDs(TT_Pic)
        
        val unusedIDs = buildList {
            for (id in ids) {
                val used = db.hasRows(TT_PicUsage) { ObjId equal id } // TODO Subquery
                
                if (!used)
                    add(id)
            }
        }
        db.deleteRows(TT_Pic) {
            Id equalAny unusedIDs
            BecameUnused less delLine
        }
    }
    
}
