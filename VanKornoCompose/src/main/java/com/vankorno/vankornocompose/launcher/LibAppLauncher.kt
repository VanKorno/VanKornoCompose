package com.vankorno.vankornocompose.launcher

import com.vankorno.vankornocompose.db.LibDbVals.AppFirstLaunched
import com.vankorno.vankornocompose.db.LibDbVals.AppLanguage
import com.vankorno.vankornocompose.db.LibDbVals.AppLastLaunched
import com.vankorno.vankornocompose.db.LibDbVals.AppLaunchCount
import com.vankorno.vankornocompose.db.LibDbVals.TTTMisc
import com.vankorno.vankornocompose.db.LibDbVals._TTTMisc
import com.vankorno.vankornocompose.db.miscTable.CMisc
import com.vankorno.vankornocompose.db.miscTable.CMisc.Int1
import com.vankorno.vankornocompose.db.miscTable.CMisc.Long1
import com.vankorno.vankornocompose.db.miscTable.MiscEntt
import com.vankorno.vankornocompose.db.miscTable.MiscTableOps.addMiscRows
import com.vankorno.vankornocompose.values.LibGlobals2.language
import com.vankorno.vankornodb.api.DbRuntime.dbh
import com.vankorno.vankornodb.api.createTable
import com.vankorno.vankornodb.get.getStr
import com.vankorno.vankornodb.misc.whereName
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
            language.value = db.getStr(TTTMisc, CMisc.Str1, whereName(AppLanguage))
            
            db.setLong(getCurrTime(), TTTMisc, Long1) { Name = AppLastLaunched }
            
            db.addToInt(1, TTTMisc, Int1) { Name = AppLaunchCount }
        }
    }
    
    
    private fun miscTable() {
        dbh.write("miscTable") { db ->
            db.createTable(_TTTMisc)
            val currTime = getCurrTime()
            
            addMiscRows(db,
                MiscEntt(AppLanguage, str1 = LangAuto),
                MiscEntt(AppFirstLaunched, long1 = currTime),
                MiscEntt(AppLastLaunched, long1 = currTime),
                MiscEntt(AppLaunchCount, 1),
            )
        }
    }
}