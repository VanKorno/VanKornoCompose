package com.vankorno.vankornocompose.fileOps.media.pic

import com.vankorno.vankornocompose._entities.pic.CPic.BecameUnused
import com.vankorno.vankornocompose._entities.pic.CPic.Path
import com.vankorno.vankornocompose._entities.pic.CPic.PreviewPath
import com.vankorno.vankornocompose._entities.pic.TTTPic
import com.vankorno.vankornocompose._entities.pic.TTTPicUsage
import com.vankorno.vankornocompose._entities.pic._TTTPic
import com.vankorno.vankornocompose._entities.usage.CUsage.ObjId
import com.vankorno.vankornocompose.db.miscTable.MiscTableOps.getDaysSinceFirstAppLaunch
import com.vankorno.vankornocompose.fileOps.LibFileOps
import com.vankorno.vankornodb.api.DbRuntime.dbh
import com.vankorno.vankornodb.delete.deleteRows
import com.vankorno.vankornodb.get.getColInts
import com.vankorno.vankornodb.get.hasRows
import com.vankorno.vankornodb.misc.data.SharedCol.cID
import com.vankorno.vankornohelpers.dLog
import com.vankorno.vankornohelpers.getCurrTime
import com.vankorno.vankornohelpers.values.LibConstants.MillisInDay

object LibPicCleanup {
    private const val TAG = "LibPicCleanup"
    
    fun getAllPicPaths(): List<String> = dbh.getTableStrings(TTTPic, arrayOf(Path, PreviewPath))
        .flatten()
        .filter { it.isNotBlank() }
    
    
    fun handlePicCleanup(                                                 daysTillDelete: Int = 10
    ) {
        val freshApp = getDaysSinceFirstAppLaunch() < 30
        if (!freshApp)
            cleanupPic(daysTillDelete)
    }
    
    fun cleanupPic(                                                       daysTillDelete: Int
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
    
    private fun deletePathLosers() = dbh.deleteRows(TTTPic) { Path equal "" }
    
    fun deleteOrphanUsages() {
        // TODO Maybe use more low-level db stuff instead
        
        dbh.write("deleteOrphanUsages") { db ->
            val picIDs = db.getColInts(TTTPicUsage, ObjId).distinct()
            
            for (picId in picIDs) {
                val exists = db.hasRows(TTTPic) { ID = picId }
                if (!exists) {
                    db.deleteRows(TTTPicUsage) { ObjId equal picId }
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
        
        dbh.deleteRows(TTTPic) { cID equalAny fileLosers }
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
    private fun getFileLoserIDs(): List<Int> {
        val objToCheck = dbh.getObjects(_TTTPic)
        
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
            val ids = dbh.getAllIDs(TTTPic)
            
            val unusedIDs = buildList {
                for (id in ids) {
                    val used = db.hasRows(TTTPicUsage) { ObjId equal id }
                    
                    if (!used)
                        add(id)
                }
            }
            db.deleteRows(TTTPic) {
                cID equalAny unusedIDs
                and { BecameUnused less delLine }
            }
        }
    }
    
}




