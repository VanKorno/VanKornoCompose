package com.vankorno.vankornocompose.fileOps

import android.content.Context
import android.net.Uri
import android.webkit.MimeTypeMap
import androidx.core.net.toUri
import com.vankorno.vankornocompose.values.LibGlobals2.lops
import java.io.File

class LibFileGetter(                                                         val context: Context
) {
    fun getFileInAppStorage(path: String): File = File(context.filesDir, path)
    
    
    fun getUriForAppFile(path: String): Uri = getFileInAppStorage(path).toUri()
    
    
    fun getAbsolutePath(path: String): String = getFileInAppStorage(path).absolutePath
    
    
    fun fileExists(file: File): Boolean = file.exists()
    
    fun fileExists(path: String): Boolean = getFileInAppStorage(path).exists()
    
}


fun getMimeType(                                                                    path: String
): String? {
    val ext = File(path).extension.lowercase()
    return  if (ext.isBlank())
                null
            else
                MimeTypeMap.getSingleton().getMimeTypeFromExtension(ext)
}


fun generateUniqueFilename(                                                       prefix: String,
                                                                                     ext: String,
): String = lops.get("", "generateUniqueFilename") {
    "${prefix}__${System.currentTimeMillis()}.$ext"
}



