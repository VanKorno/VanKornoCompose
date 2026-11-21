package com.vankorno.vankornocompose.launcher

import com.vankorno.vankornocompose._entities.misc.MiscData
import com.vankorno.vankornocompose.db.LibDbVals.AppFirstLaunched
import com.vankorno.vankornocompose.db.LibDbVals.AppLanguage
import com.vankorno.vankornocompose.db.LibDbVals.AppLastLaunched
import com.vankorno.vankornocompose.db.LibDbVals.AppLaunchCount
import com.vankorno.vankornocompose.db.LibDbVals.TT_Misc
import com.vankorno.vankornocompose.values.LibGlobals2.language
import com.vankorno.vankornodb.add.addEntities
import com.vankorno.vankornodb.api.DbRuntime.dbh
import com.vankorno.vankornodb.get.getStr
import com.vankorno.vankornodb.newTable.createTable
import com.vankorno.vankornodb.set.addToInt
import com.vankorno.vankornodb.set.setLong
import com.vankorno.vankornohelpers.getCurrTime
import com.vankorno.vankornohelpers.values.LibLangConst.LangAuto

internal object LibAppLauncher {
    fun beforeFirstLaunch() {
        miscTable()
        language.value = LangAuto
    }
    
    fun beforeNotFirstLaunch() {
        dbh.write("beforeNotFirstLaunch") { db ->
            language.value = db.getStr(TT_Misc.col { Str1 }) { Name equal AppLanguage }
            
            db.setLong(getCurrTime(), TT_Misc.col { Long1 }) { Name equal AppLastLaunched }
            
            db.addToInt(1, TT_Misc.col { Int1 }) { Name equal AppLaunchCount }
        }
    }
    
    
    private fun miscTable() {
        dbh.write("miscTable") { db ->
            db.createTable(TT_Misc)
            val currTime = getCurrTime()
            
            db.addEntities(TT_Misc,
                MiscData(AppLanguage, str1 = LangAuto),
                MiscData(AppFirstLaunched, long1 = currTime),
                MiscData(AppLastLaunched, long1 = currTime),
                MiscData(AppLaunchCount, 1),
            )
        }
    }
}
