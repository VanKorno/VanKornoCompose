package com.vankorno.vankornocompose

import android.app.Application
import android.content.pm.ApplicationInfo
import com.vankorno.vankornocompose.navig.BaseBackNavigator
import com.vankorno.vankornocompose.navig.BaseNavigator
import com.vankorno.vankornocompose.navig.BaseScreenUpdater
import com.vankorno.vankornocompose.navig.Navig
import com.vankorno.vankornocompose.navig.Navig.goTo
import com.vankorno.vankornocompose.navig.Navig.goToStart
import com.vankorno.vankornocompose.navig.ScrHome
import com.vankorno.vankornocompose.values.LibGlobals2.appContext
import com.vankorno.vankornocompose.values.LibGlobals2.libVm
import com.vankorno.vankornocompose.values.LibGlobals2.soundPoolHelper
import com.vankorno.vankornocompose.values.LibLambdas2.popupOFF
import com.vankorno.vankornocompose.values.internal.DummyEntityMeta
import com.vankorno.vankornodb.api.DbHelper
import com.vankorno.vankornodb.api.DbRuntime.dbh
import com.vankorno.vankornodb.core.data.DbConstants.InMemoryDB
import com.vankorno.vankornohelpers.LibMisc
import com.vankorno.vankornohelpers.LibSoundPool
import com.vankorno.vankornohelpers.dLog
import com.vankorno.vankornohelpers.values.LibGlobals.debugBuild
import com.vankorno.vankornohelpers.values.LibLambdas.minimizeApp
import com.vankorno.vankornohelpers.values.LibLambdas.playSound

private const val TAG = "LibApp"

abstract class LibApp(                         val dbName: String = "database",
                                            val navigator: BaseNavigator = DefaultNavigator,
                                        val backNavigator: BaseBackNavigator = DefaultBackNavigator,
                                        val screenUpdater: BaseScreenUpdater = DefaultUpdater,
                                         val soundsToInit: Array<Int> = emptyArray(),
) : Application() {
    
    /** Full override. */
    protected open fun doOnCreate() {}
    
    /**
     * Creates a dummy in-memory db if not overriden. Full override recommended.
     * Put [actualDbName] as dbName in DbHelper. By this point it contains either a full db file name
     * or (if running tests) in-memory db string.
     */
    protected open fun dbInit(                                              actualDbName: String
    ) {
        dbh = DbHelper(
            context = this,
            dbName = InMemoryDB,
            dbVersion = 1,
            entityMeta = DummyEntityMeta.entries,
        )
    }
    
    private fun initializeDb() {
        val fullDbFileName = dbName + ".db"
        val actualDbName = LibMisc().getDbName(fullDbFileName)
        // region LOG
            dLog(TAG, "dbInit(): Actual db name = $actualDbName")
        // endregion
        dbInit(actualDbName)
    }
    
    
    
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        
        debugBuild = (applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE) != 0
        
        initializeDb()
        doOnCreate()
        initLambdas()
        soundInit()
    }
    
    private fun initLambdas() {
        Navig.init(
            goTo = { navigator.onGoToScreen(it) },
            goBack = { backNavigator.onGoBack() },
            goToStart = { navigator.onGoToStart() },
            updateScreen = { screenUpdater.updateScr() },
        )
    }
    
    
    private fun soundInit() {
        soundPoolHelper = LibSoundPool(this, soundsToInit)
        
        playSound = { sound ->
            soundPoolHelper.playSound(sound, this)
        }
    }
    
    /** Full override. */
    protected open fun beforeTermination() {}
    /** Full override. */
    protected open fun afterTermination() {}
    
    override fun onTerminate() {
        // region LOG
            dLog(TAG, "onTerminate()")
        // endregion
        beforeTermination()
        
        soundPoolHelper.release()
        dbh.closeDb()
        
        super.onTerminate()
        
        afterTermination()
    }
}





private object DefaultNavigator : BaseNavigator()
private object DefaultUpdater : BaseScreenUpdater()

private object DefaultBackNavigator : BaseBackNavigator() {
    override fun onBackFromPopup() {
        popupOFF()
    }
    override fun onBackWithoutPopup() {
        val currScr = libVm.currScreen.value
        val previousScr = libVm.previousScreen.value
        
        when (currScr) {
            ScrHome -> minimizeApp()
            previousScr -> goToStart()
            else -> goTo(previousScr)
        }
    }
}


