package com.vankorno.vankornocompose.db.miscTable

import com.vankorno.vankornocompose.db.LibDbVals.AppFirstLaunched
import com.vankorno.vankornocompose.db.LibDbVals.TTTMisc
import com.vankorno.vankornocompose.db.miscTable.CMisc.Long1
import com.vankorno.vankornodb.api.DbRuntime.dbh
import com.vankorno.vankornohelpers.getCurrTime
import com.vankorno.vankornohelpers.values.LibConstants.MillisInDay

object MiscTableOps {
    
    fun getDaysSinceFirstAppLaunch(): Int {
        val currTime = getCurrTime()
        val firstLaunchTime = dbh.getLong(TTTMisc, Long1) { Name = AppFirstLaunched }
        
        val diff = currTime - firstLaunchTime
        
        if (diff < MillisInDay)
            return 0
        
        return (diff / MillisInDay).toInt()
    }
    
}