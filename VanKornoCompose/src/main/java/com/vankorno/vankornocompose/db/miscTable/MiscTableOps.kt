package com.vankorno.vankornocompose.db.miscTable

import android.database.sqlite.SQLiteDatabase
import com.vankorno.vankornocompose.db.LibDbVals.AppFirstLaunched
import com.vankorno.vankornocompose.db.LibDbVals.TT_Misc
import com.vankorno.vankornodb.api.DbRuntime.dbh
import com.vankorno.vankornodb.get.getLong
import com.vankorno.vankornohelpers.getDaysTillNow

object MiscTableOps {
    
    fun getDaysSinceFirstAppLaunch(): Int = dbh.read(-1, "getDaysSinceFirstAppLaunch") { db ->
        getDaysSinceFirstAppLaunch(db)
    }
    
    fun getDaysSinceFirstAppLaunch(                                             db: SQLiteDatabase
    ): Int {
        val firstLaunchTime = db.getLong(TT_Misc.col{ Long1 }) { Name equal AppFirstLaunched }
        
        return firstLaunchTime.getDaysTillNow()
    }
    
}
