package com.vankorno.appforcomposelib

import com.vankorno.appforcomposelib._entities.DbNAME
import com.vankorno.appforcomposelib._entities.DbVersion
import com.vankorno.appforcomposelib._entities.EntityMeta
import com.vankorno.appforcomposelib._navig.Navigator
import com.vankorno.vankornocompose.LibApp
import com.vankorno.vankornodb.api.DbHelper
import com.vankorno.vankornodb.api.DbRuntime.dbh
import com.vankorno.vankornohelpers.values.LibGlobals.UserTester
import com.vankorno.vankornohelpers.values.LibGlobals.appUser

private const val TAG = "DemoApp"

class DemoApp : LibApp(
    dbName = DbNAME,
    navigator = Navigator
) {
    
    override fun dbInit(                                                    actualDbName: String
    ) {
        dbh = DbHelper(
            context = this,
            dbName = actualDbName,
            dbVersion = DbVersion,
            entityMeta = EntityMeta.entries,
            onUpgrade = { db, _ ->
                
            }
        )
    }
    
    override fun doOnCreate() {
        appUser = UserTester
    }
}
