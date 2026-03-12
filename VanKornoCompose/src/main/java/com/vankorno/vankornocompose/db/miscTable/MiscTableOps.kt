package com.vankorno.vankornocompose.db.miscTable

import com.vankorno.vankornocompose.db.LibDbVals.AppFirstLaunched
import com.vankorno.vankornocompose.db.LibDbVals.TTTMisc
import com.vankorno.vankornocompose.db.miscTable.CMisc.Long1
import com.vankorno.vankornodb.api.DbRuntime.dbh
import com.vankorno.vankornohelpers.getDaysTillNow

object MiscTableOps {
    
    fun getDaysSinceFirstAppLaunch(): Int {
        val firstLaunchTime = dbh.getLong(TTTMisc, Long1) { Name = AppFirstLaunched }
        
        return firstLaunchTime.getDaysTillNow()
    }
    
}