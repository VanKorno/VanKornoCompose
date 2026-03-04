package com.vankorno.appforcomposelib.AppStart

import com.vankorno.appforcomposelib._entities.DbNAME
import com.vankorno.appforcomposelib._entities.DbVersion
import com.vankorno.appforcomposelib._entities.EntityMeta
import com.vankorno.vankornocompose.LibApp
import com.vankorno.vankornocompose.navig.Screen
import com.vankorno.vankornodb.api.DbHelper
import com.vankorno.vankornodb.api.DbRuntime.dbh
import com.vankorno.vankornohelpers.LibMisc
import com.vankorno.vankornohelpers.dLog

private const val TAG = "DemoApp"

class DemoApp : LibApp() {
    
    override fun dbInit() {
        val dbName = LibMisc().getDbName(DbNAME)
        // region LOG
            dLog(TAG, "dbInit() dbName = $dbName")
        // endregion
        dbh = DbHelper(
            context = this,
            dbName = dbName,
            dbVersion = DbVersion,
            entityMeta = EntityMeta.entries,
            onUpgrade = { db, _ ->
                
            }
        )
    }
    
    override fun onGoTo(scr: Screen) {
        
    }
    
    override fun onGoBack() {
        
    }
    
    override fun onGoToStart() {
        
    }
    
    override fun onUpdateScreen() {
        
    }
    
    
}