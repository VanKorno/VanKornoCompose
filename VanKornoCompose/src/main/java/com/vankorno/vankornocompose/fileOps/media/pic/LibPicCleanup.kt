package com.vankorno.vankornocompose.fileOps.media.pic

import com.vankorno.vankornocompose._entities.pic.TT_Pic
import com.vankorno.vankornocompose._entities.pic.TT_PicUsage
import com.vankorno.vankornocompose.db.miscTable.MiscTableOps.getDaysSinceFirstAppLaunch
import com.vankorno.vankornocompose.fileOps.LibFileOps
import com.vankorno.vankornodb.api.DbRuntime.dbh
import com.vankorno.vankornodb.delete.deleteRows
import com.vankorno.vankornodb.get.getAllIDs
import com.vankorno.vankornodb.get.getColLongs
import com.vankorno.vankornodb.get.getEntities
import com.vankorno.vankornodb.get.getStr
import com.vankorno.vankornodb.get.getTableStrings
import com.vankorno.vankornodb.get.hasRows
import com.vankorno.vankornodb.misc.suppressValGetterErrorLog
import com.vankorno.vankornohelpers.dLog
import com.vankorno.vankornohelpers.getCurrTime
import com.vankorno.vankornohelpers.values.LibConstants.MillisInDay

object LibPicCleanup {
    private const val TAG = "LibPicCleanup"
    
    fun singleSubjPicCleanup(                                              subjTableName: String,
                                                                                  subjId: Long,
    ) {
        val picIDs = dbh.getColLongs(TT_PicUsage.col { ObjId },
            where = {
                SubjId equal subjId
                SubjTable equal subjTableName
            }
        )
        if (picIDs.isEmpty()) return //\/\/\/\/\/\
        
        for (picId in picIDs) {
            if (isPicBroken(picId)) {
                cleanupPic()
                break //\/\/\
            }
        }
    }
    
    fun isPicBroken(                                                               picId: Long
    ): Boolean {
        suppressValGetterErrorLog = true
        val path = dbh.getStr(TT_Pic.col { Path }) { Id equal picId }
        suppressValGetterErrorLog = false
        
        if (path.isBlank()) return true //\/\/\/\/\/\
        
        val fileMissing = !LibFileOps.fileExists(path)
        
        return fileMissing
    }
    
    
    fun getAllPicPaths(): List<String> = dbh.getTableStrings(TT_Pic.columns { Path then PreviewPath })
        .flatten()
        .filter { it.isNotBlank() }
    
    
    fun handlePicCleanup(                                                 daysTillDelete: Int = 10
    ) {
        val freshApp = getDaysSinceFirstAppLaunch() < 30
        if (!freshApp)
            cleanupPic(daysTillDelete)
    }
    
    fun cleanupPic(                                                       daysTillDelete: Int = 10
    ) {
        // region LOG
            dLog(TAG, "cleanupPic()")
        // endregion
        deleteBrokenPics()
        deleteUnusedPicsInDb(daysTillDelete)
        deleteOrphanPicFiles()
    }
    
    fun deleteBrokenPics() {
        // region LOG
            dLog(TAG, "deleteBrokenPics()")
        // endregion
        deletePathLosers()
        deleteFileLosers()
        deleteOrphanUsages()
    }
    
    private fun deletePathLosers() = dbh.deleteRows(TT_Pic) { Path equal "" }
    
    fun deleteOrphanUsages() {
        // TODO Maybe use more low-level db stuff instead
        
        dbh.write("deleteOrphanUsages") { db ->
            val picIDs = db.getColLongs(TT_PicUsage.col { ObjId }).distinct()
            
            for (picId in picIDs) {
                val exists = db.hasRows(TT_Pic) { Id equal picId }
                if (!exists) {
                    db.deleteRows(TT_PicUsage) { ObjId equal picId }
                }
            }
        }
    }
    
    
    private fun deleteFileLosers() {
        // region LOG
            dLog(TAG, "deleteFileLosers()")
        // endregion
        val fileLosers = getFileLoserIDs()
        if (fileLosers.isEmpty()) return //\/\/\/\/\/\
        
        dbh.deleteRows(TT_Pic) { Id equalAny fileLosers }
    }
    
    
    fun deleteOrphanPicFiles() {
        // region LOG
            dLog(TAG, "deleteOrphanPicFiles()")
        // endregion
        val fileNames = LibFileOps.getAllFileNames(PicDirName)
        
        val dbFileNames = getAllPicPaths()
                            .map { it.substringAfterLast('/') }
                            .toSet()
        
        for (fileName in fileNames) {
            if (fileName !in dbFileNames) {
                LibFileOps.deleteFile(PicDirName, fileName)
            }
        }
    }
    
    
    /**
     * PicEntt rows that are in use, but don't have the actual files.
     */
    private fun getFileLoserIDs(): List<Long> {
        val objToCheck = dbh.getEntities(TT_Pic)
        
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
    
    
    fun deleteUnusedPicsInDb(                                             daysTillDelete: Int
    ) {
        val millisTillDelete = daysTillDelete * MillisInDay
        val delLine = getCurrTime() - millisTillDelete
        
        dbh.write("DeleteUnusedPics") { db ->
            val ids = dbh.getAllIDs(TT_Pic)
            
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
    
}




