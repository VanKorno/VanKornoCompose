package com.vankorno.vankornocompose.db.miscTable

import com.vankorno.vankornocompose.db.LibDbVals.AppFirstLaunched
import com.vankorno.vankornocompose.db.LibDbVals.TTTMisc
import com.vankorno.vankornodb.api.DbRuntime.dbh
import com.vankorno.vankornodb.get.getLong
import com.vankorno.vankornohelpers.getDaysTillNow

object MiscTableOps {
    
    fun getDaysSinceFirstAppLaunch(): Int {
        val firstLaunchTime = dbh.getLong(TTTMisc.col { Long1 }) { Name equal AppFirstLaunched }
        
        return firstLaunchTime.getDaysTillNow()
    }
    
    
    
}
