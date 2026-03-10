package com.vankorno.vankornocompose.fileOps

import android.content.Context
import android.net.Uri
import android.webkit.MimeTypeMap
import androidx.core.net.toUri
import java.io.File

object FileUtils {
    
    fun getMimeType(path: String): String? =
        MimeTypeMap
            .getSingleton()
            .getMimeTypeFromExtension(File(path).extension.lowercase())
    
    
    fun fileExists(file: File): Boolean = file.exists()
    
    
    fun fileExists(                                                              context: Context,
                                                                                    path: String,
    ): Boolean = getFileInAppStorage(context, path).exists()
    
    
    fun getFileInAppStorage(                                                     context: Context,
                                                                                    path: String,
    ): File = File(context.filesDir, path)
    
    fun getUriForAppFile(                                                        context: Context,
                                                                                    path: String,
    ): Uri = getFileInAppStorage(context, path).toUri()
    
    
    
}