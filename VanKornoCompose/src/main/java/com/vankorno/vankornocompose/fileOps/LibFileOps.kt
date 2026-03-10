package com.vankorno.vankornocompose.fileOps

import android.net.Uri
import com.vankorno.vankornocompose.values.LibGlobals2.appContext
import com.vankorno.vankornohelpers.dLog
import java.io.File
import java.io.FileOutputStream

object LibFileOps {
    private const val TAG = "LibFileOps"
    
    private fun getDirInstance(                                                  dirName: String
    ): File {
        // region LOG
            dLog(TAG, "getDirInstance(dirName = $dirName")
        // endregion
        return File(appContext.filesDir, dirName)
    }
    
    
    fun getAllFileNames(                                                         dirName: String
    ): List<String> {
        // region LOG
            dLog(TAG, "getAllFileNames(dirName = $dirName")
        // endregion
        return getDirInstance(dirName).listFiles()?.map { "$dirName/${it.name}" }.orEmpty()
    }
    
    
    fun deleteAllFiles(                                                          dirName: String
    ): Int {
        // region LOG
            dLog(TAG, "deleteAllFiles(dirName = $dirName")
        // endregion
        return getDirInstance(dirName).listFiles()?.count { it.delete() } ?: 0
    }
    
    
    fun fileExists(                                                              dirName: String,
                                                                                    name: String,
    ): Boolean {
        // region LOG
            dLog(TAG, "fileExists(dirName = $dirName, name = $name")
        // endregion
        return File(getDirInstance(dirName), name).exists()
    }
    
    
    fun deleteFile(                                                              dirName: String,
                                                                                    name: String,
    ): Boolean {
        // region LOG
            dLog(TAG, "deleteFile(dirName = $dirName, name = $name")
        // endregion
        val file = File(getDirInstance(dirName), name)
        return file.exists() && file.delete()
    }
    
    
    fun renameFile(                                                              dirName: String,
                                                                                 oldName: String,
                                                                                 newName: String,
    ): String? {
        // region LOG
            dLog(TAG, "renameFile(dirName = $dirName, oldName = $oldName, newName = $newName")
        // endregion
        val oldFile = File(getDirInstance(dirName), oldName)
        if (!oldFile.exists()) return null //\/\/\/\/\/\
        
        val ext = oldFile.extension
        val newFile = File(
            getDirInstance(dirName),
            if (ext.isBlank()) newName else "$newName.$ext"
        )
        
        return if (oldFile.renameTo(newFile))
                    "$dirName/${newFile.name}"
               else
                    null
    }
    
    
    fun writeTextToFile(                                                         dirName: String,
                                                                                    name: String,
                                                                                    text: String,
    ): String {
        // region LOG
            dLog(TAG, "writeTextToFile(dirName = $dirName, name = $name")
        // endregion
        val file = File(getDirInstance(dirName), name)
        file.writeText(text)
        return "$dirName/$name"
    }
    
    
    fun readTextFromFile(                                                        dirName: String,
                                                                                    name: String,
    ): String? {
        // region LOG
            dLog(TAG, "readTextFromFile(dirName = $dirName, name = $name)")
        // endregion
        return try {
            File(getDirInstance(dirName), name).readText()
        } catch (e: Exception) {
            null
        }
    }
    
    
    fun saveFileFromUri(                                                         dirName: String,
                                                                                     uri: Uri,
                                                                                    name: String,
    ): String? {
        // region LOG
            dLog(TAG, "saveFileFromUri(dirName = $dirName, name = $name)")
        // endregion
        val file = File(getDirInstance(dirName), name)
        
        return try {
            appContext.contentResolver.openInputStream(uri)?.use { input ->
                FileOutputStream(file).use { out ->
                    input.copyTo(out)
                }
            } ?: return null
            
            "$dirName/$name"
        } catch (e: Exception) {
            null
        }
    }
    
    
    
}