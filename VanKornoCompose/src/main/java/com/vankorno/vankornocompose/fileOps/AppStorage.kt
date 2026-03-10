package com.vankorno.vankornocompose.fileOps

import android.content.Context

class AppStorage(                                                                context: Context
) {
    val files = AppFiles(context)
    
    fun pics(block: LibFileOps.()->Unit) = files.dir("user_pics", block)
    
    fun exports(block: LibFileOps.()->Unit) = files.dir("exports", block)
    
}