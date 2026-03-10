package com.vankorno.vankornocompose.fileOps

import android.content.Context

class AppStorage(                                                                context: Context
) {
    val files = AppFiles(context)
    
    fun pics(block: FileOps.()->Unit) = files.dir("user_pics", block)
    
    fun exports(block: FileOps.()->Unit) = files.dir("exports", block)
    
}