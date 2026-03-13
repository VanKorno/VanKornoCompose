package com.vankorno.vankornocompose.db

import com.vankorno.vankornocompose.db.miscTable._Misc
import com.vankorno.vankornodb.dbManagement.data.using

object LibDbVals {
    const val TTTMisc = "TTTMisc"
    
    val _TTTMisc = TTTMisc using _Misc
    
    const val AppLanguage = "AppLanguage"
    const val AppFirstLaunched = "AppFirstLaunched"
    const val AppLastLaunched = "AppLastLaunched"
    const val AppLaunchCount = "AppLaunchCount"
    
    
    
}