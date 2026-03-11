package com.vankorno.vankornocompose.fileOps.media

import com.vankorno.vankornocompose._entities.pic.CPic.BecameUnused
import com.vankorno.vankornocompose._entities.pic.CPic.Path
import com.vankorno.vankornocompose._entities.pic.CPic.PreviewPath
import com.vankorno.vankornocompose._entities.pic.CPic.Usages
import com.vankorno.vankornocompose._entities.pic.PicEntt
import com.vankorno.vankornocompose._entities.pic.TTTPic
import com.vankorno.vankornocompose._entities.pic.getPic
import com.vankorno.vankornocompose.fileOps.LibFileOps
import com.vankorno.vankornodb.api.DbRuntime.dbh
import com.vankorno.vankornohelpers.dLog
import com.vankorno.vankornohelpers.getCurrTime

object LibPicDb {
    private const val TAG = "LibPicDb"
    private const val DaysTillDelete = 10
    
    fun getPic(id: Int ) = dbh.read(PicEntt(), "getPic") { db -> db.getPic(id) }
    
    
    fun deleteOrphanPicFiles() {
        // region LOG
            dLog(TAG, "deleteOrphanPicFiles()")
        // endregion
        val fileNames = LibFileOps.getAllFileNames(PicDirName)
        
        val dbFiles = getAllPicPaths()
                            .map { it.substringAfterLast('/') }
                            .toSet()
        
        for (fileName in fileNames) {
            if (fileName !in dbFiles) {
                LibFileOps.deleteFile(PicDirName, fileName)
            }
        }
    }
    
    
    fun getAllPicPaths(): List<String> = dbh.getTableStrings(TTTPic, arrayOf(Path, PreviewPath))
        .flatten()
        .filter { it.isNotBlank() }
    
    
    
    fun cleanupPic() {
        // region LOG
            dLog(TAG, "cleanupPic()")
        // endregion
        val millisTillDelete = DaysTillDelete * 24 * 60 * 60 * 1000L
        val delLine = getCurrTime() - millisTillDelete
        
        dbh.deleteRows(TTTPic) {
            Usages less 1
            and { BecameUnused less delLine }
        }
        deleteOrphanPicFiles()
    }
    
}