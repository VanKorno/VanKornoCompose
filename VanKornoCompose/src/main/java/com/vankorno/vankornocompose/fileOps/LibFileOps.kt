package com.vankorno.vankornocompose.fileOps

import android.R.attr.name
import android.net.Uri
import android.webkit.MimeTypeMap
import androidx.core.net.toUri
import com.vankorno.vankornocompose.values.LibGlobals2.appContext
import com.vankorno.vankornocompose.values.LibGlobals2.lops
import com.vankorno.vankornohelpers.dLog
import com.vankorno.vankornohelpers.eLog
import com.vankorno.vankornohelpers.wLog
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
                                                                                fileName: String,
    ): Boolean {
        // region LOG
            dLog(TAG, "fileExists(dirName = $dirName, name = $fileName")
        // endregion
        return File(getDirInstance(dirName), fileName).exists()
    }
    
    fun fileExists(                                                                 file: File
    ): Boolean {
        // region LOG
            dLog(TAG, "fileExists(file = ${file.absolutePath})")
        // endregion
        return file.exists()
    }
    
    fun fileExists(                                                                 path: String
    ): Boolean {
        // region LOG
            dLog(TAG, "fileExists(path = $path)")
        // endregion
        return getFile(path).exists()
    }
    
    
    fun getFile(                                                                    path: String
    ): File {
        // region LOG
            dLog(TAG, "getFile(path = $path)")
        // endregion
        return File(appContext.filesDir, path)
    }
    
    fun getUriForFile(                                                              path: String
    ): Uri {
        // region LOG
            dLog(TAG, "getUriForFile(path = $path)")
        // endregion
        return getFile(path).toUri()
    }
    
    fun getAbsolutePath(path: String): String = getFile(path).absolutePath
    
    
    
    fun deleteFile(                                                              dirName: String,
                                                                                fileName: String,
    ): Boolean {
        // region LOG
            dLog(TAG, "deleteFile(dirName = $dirName, name = $fileName")
        // endregion
        val file = File(getDirInstance(dirName), fileName)
        val deleted = file.exists() && file.delete()
        if (!deleted) {
            // region LOG
                wLog(TAG, "deleteFile() failed for $fileName")
            // endregion
        }
        return deleted
    }
    
    
    fun renameFile(                                                              dirName: String,
                                                                                 oldName: String,
                                                                                 newName: String,
    ): String? {
        // region LOG
            dLog(TAG, "renameFile(dirName = $dirName, oldName = $oldName, newName = $newName")
        // endregion
        val oldFile = File(getDirInstance(dirName), oldName)
        
        if (!oldFile.exists()) {
            // region LOG
                eLog(TAG, "renameFile(): old file does not exist: $oldName")
            // endregion
            return null //\/\/\/\/\/\
        }
        
        val ext = oldFile.extension
        val newFile = File(
            getDirInstance(dirName),
            if (ext.isBlank()) newName else "$newName.$ext"
        )
        
        return if (oldFile.renameTo(newFile)) {
                    "$dirName/${newFile.name}"
                } else {
                    // region LOG
                        eLog(TAG, "renameFile(): rename failed for $oldName -> $newName")
                    // endregion
                    null
               }
    }
    
    
    fun writeTextToFile(                                                         dirName: String,
                                                                                fileName: String,
                                                                                    text: String,
    ): String {
        // region LOG
            dLog(TAG, "writeTextToFile(dirName = $dirName, name = $fileName")
        // endregion
        val file = File(getDirInstance(dirName), fileName)
        file.writeText(text)
        return "$dirName/$fileName"
    }
    
    
    fun readTextFromFile(                                                        dirName: String,
                                                                                fileName: String,
    ): String? {
        // region LOG
            dLog(TAG, "readTextFromFile(dirName = $dirName, name = $fileName)")
        // endregion
        return try {
            File(getDirInstance(dirName), fileName).readText()
        } catch (e: Exception) {
            // region LOG
                eLog(TAG, "readTextFromFile() failed for $fileName", e)
            // endregion
            null
        }
    }
    
    
    fun saveFileFromUri(                                                  dirName: String,
                                                                              uri: Uri,
                                                                           prefix: String = "file",
    ): String? {
        // region LOG
            dLog(TAG, "saveFileFromUri(dirName = $dirName, name = $name)")
        // endregion
        val ext = getExtensionFromUri(uri) ?: "bin"
        val fileName = generateUniqueFilename(prefix, ext)
        val file = File(getDirInstance(dirName), fileName)
        
        return try {
            appContext.contentResolver.openInputStream(uri)?.use { input ->
                FileOutputStream(file).use { out ->
                    input.copyTo(out)
                }
            } ?: run {
                // region LOG
                    eLog(TAG, "saveFileFromUri(): input stream is null for $fileName")
                // endregion
                return null //\/\/\/\/\/\
            }
            "$dirName/$fileName"
        } catch (e: Exception) {
            // region LOG
                eLog(TAG, "saveFileFromUri() failed for $fileName", e)
            // endregion
            null
        }
    }
    
    
    fun getExtensionFromUri(                                                         uri: Uri
    ): String? {
        val type = appContext.contentResolver.getType(uri) ?: return null
        return MimeTypeMap.getSingleton().getExtensionFromMimeType(type)
    }
    
    
    fun getMimeType(                                                                path: String
        ): String? {
        val ext = File(path).extension.lowercase()
        return  if (ext.isBlank())
                    null
                else
                    MimeTypeMap.getSingleton().getMimeTypeFromExtension(ext)
    }
    
    
    fun generateUniqueFilename(                                                   prefix: String,
                                                                                     ext: String,
    ): String = lops.get("", "generateUniqueFilename") {
        "${prefix}__${System.currentTimeMillis()}.$ext"
    }
    
}