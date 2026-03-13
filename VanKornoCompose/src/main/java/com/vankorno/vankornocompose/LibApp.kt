package com.vankorno.vankornocompose

import android.app.Application
import android.content.pm.ApplicationInfo
import com.vankorno.vankornocompose.navig.Navig
import com.vankorno.vankornocompose.navig.Screen
import com.vankorno.vankornocompose.values.LibGlobals2.appContext
import com.vankorno.vankornocompose.values.LibGlobals2.soundPoolHelper
import com.vankorno.vankornocompose.values.internal.DummyEntityMeta
import com.vankorno.vankornodb.api.DbHelper
import com.vankorno.vankornodb.api.DbRuntime.dbh
import com.vankorno.vankornodb.core.data.DbConstants.InMemoryDB
import com.vankorno.vankornohelpers.LibSoundPool
import com.vankorno.vankornohelpers.dLog
import com.vankorno.vankornohelpers.values.LibGlobals.debugBuild
import com.vankorno.vankornohelpers.values.LibGlobals.updatingScreenNow
import com.vankorno.vankornohelpers.values.LibLambdas.playSound

private const val TAG = "LibApp"

abstract class LibApp(                                   val soundsToInit: Array<Int> = emptyArray()
) : Application() {
    
    protected open fun doOnCreate() {}
    protected open fun dbInit() {
        dbh = DbHelper(
            context = this,
            dbName = InMemoryDB,
            dbVersion = 1,
            entityMeta = DummyEntityMeta.entries,
        )
    }
    
    protected abstract fun onGoTo(scr: Screen)
    protected abstract fun onGoBack()
    protected abstract fun onGoToStart()
    protected abstract fun onUpdateScreen()
    
    
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        
        debugBuild = (applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE) != 0
        
        dbInit()
        doOnCreate()
        initLambdas()
        soundInit()
    }
    
    private fun initLambdas() {
        Navig.init(
            goTo = { onGoTo(it) },
            goBack = { onGoBack() },
            goToStart = { onGoToStart() },
            updateScreen = {
                updatingScreenNow = true
                onUpdateScreen()
                updatingScreenNow = false
            },
        )
    }
    
    
    private fun soundInit() {
        soundPoolHelper = LibSoundPool(this, soundsToInit)
        
        playSound = { sound ->
            soundPoolHelper.playSound(sound, this)
        }
    }
    
    
    protected open fun doOnTerminate() {}
    
    override fun onTerminate() {
        super.onTerminate()
        // region LOG
            dLog(TAG, "onTerminate()")
        // endregion
        doOnTerminate()
        soundPoolHelper.release()
        dbh.closeDb()
    }
}