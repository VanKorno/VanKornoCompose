package com.vankorno.vankornocompose.launcher

import com.vankorno.vankornocompose.db.LibDbVals.AppFirstLaunched
import com.vankorno.vankornocompose.db.LibDbVals.AppLanguage
import com.vankorno.vankornocompose.db.LibDbVals.TTTMisc
import com.vankorno.vankornocompose.db.LibDbVals._TTTMisc
import com.vankorno.vankornocompose.db.miscTable.CMisc
import com.vankorno.vankornocompose.db.miscTable.MiscEntt
import com.vankorno.vankornocompose.values.LibGlobals2.language
import com.vankorno.vankornodb.api.DbRuntime.dbh
import com.vankorno.vankornodb.api.createTable
import com.vankorno.vankornodb.misc.whereName
import com.vankorno.vankornohelpers.getCurrTime
import com.vankorno.vankornohelpers.values.LibLangConst.LangAuto


internal object LibAppLauncher {
    fun beforeFirstLaunch() {
        miscTable()
        language.value = LangAuto
    }
    
    fun beforeNotFirstLaunch() {
        language.value = dbh.getStr(TTTMisc, CMisc.Str1, whereName(AppLanguage))
    }
    
    
    private fun miscTable() {
        dbh.write("miscTable") { db ->
            db.createTable(_TTTMisc)
            
            MiscEntt(AppLanguage, str1 = LangAuto).insert(db)
            MiscEntt(AppFirstLaunched, long1 = getCurrTime()).insert(db)
        }
    }
}