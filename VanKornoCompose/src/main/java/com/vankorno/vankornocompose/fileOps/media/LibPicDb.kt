package com.vankorno.vankornocompose.fileOps.media

import com.vankorno.vankornocompose._entities.pic.CPic.Path
import com.vankorno.vankornocompose._entities.pic.CPic.PreviewPath
import com.vankorno.vankornocompose._entities.pic.PicEntt
import com.vankorno.vankornocompose._entities.pic.TTTPic
import com.vankorno.vankornocompose._entities.pic.getPic
import com.vankorno.vankornocompose.fileOps.LibFileOps
import com.vankorno.vankornodb.api.DbRuntime.dbh
import com.vankorno.vankornohelpers.dLog

object LibPicDb {
    private const val TAG = "LibPicDb"
    
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
    
    
    
    
    
    
}