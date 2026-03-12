package com.vankorno.vankornocompose.fileOps.media

import com.vankorno.vankornocompose._entities.pic.CPic.BecameUnused
import com.vankorno.vankornocompose._entities.pic.CPic.Path
import com.vankorno.vankornocompose._entities.pic.CPic.PreviewPath
import com.vankorno.vankornocompose._entities.pic.CPic.Usages
import com.vankorno.vankornocompose._entities.pic.PicEntt
import com.vankorno.vankornocompose._entities.pic.TTTPic
import com.vankorno.vankornocompose._entities.pic._TTTPic
import com.vankorno.vankornocompose._entities.pic.getPic
import com.vankorno.vankornocompose.fileOps.LibFileOps
import com.vankorno.vankornodb.api.DbRuntime.dbh
import com.vankorno.vankornodb.misc.data.SharedCol.cID
import com.vankorno.vankornohelpers.dLog
import com.vankorno.vankornohelpers.getCurrTime
import com.vankorno.vankornohelpers.values.LibConstants.MillisInDay

object LibPicDb {
    private const val TAG = "LibPicDb"
    private const val DaysTillDelete = 10
    
    fun getPic(id: Int ) = dbh.read(PicEntt(), "getPic") { db -> db.getPic(id) }
    
    
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
    
    
    fun getAllPicPaths(): List<String> = dbh.getTableStrings(TTTPic, arrayOf(Path, PreviewPath))
        .flatten()
        .filter { it.isNotBlank() }
    
    
    
    fun cleanupPic(                                            doForEachFileLoser: (Int)->Unit = {}
    ) {
        // region LOG
            dLog(TAG, "cleanupPic()")
        // endregion
        val millisTillDelete = DaysTillDelete * MillisInDay
        val delLine = getCurrTime() - millisTillDelete
        
        dbh.deleteRows(TTTPic) {
            Usages less 1
            and { BecameUnused less delLine }
        }
        handleFileLosers(doForEachFileLoser)
        
        deleteOrphanPicFiles()
    }
    
    private fun handleFileLosers(                     doForEachFileLoserBeforeDel: (Int)->Unit = {}
    ) {
        // region LOG
            dLog(TAG, "handleFileLosers()")
        // endregion
        val fileLosers = getFileLoserIDs()
        
        if (fileLosers.isEmpty()) return //\/\/\/\/\/\
        
        for (loserId in fileLosers) {
            doForEachFileLoserBeforeDel(loserId)
        }
        dbh.deleteRows(TTTPic) {
            cID less 0 // just to simplify the loop
            
            for (loserId in fileLosers) {
                or { ID = loserId }
            }
        }
    }
    
    
    /**
     * PicEntt rows that are in use, but don't have the actual files.
     */
    private fun getFileLoserIDs(): List<Int> {
        val objToCheck = dbh.getObjects(_TTTPic) {
            Usages greater 0
            and { Path notEqual "" } // Just in case
        }
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
}