package com.vankorno.vankornocompose

import android.app.Application
import android.content.pm.ApplicationInfo
import com.vankorno.vankornocompose.values.LibGlobals2.dbh
import com.vankorno.vankornocompose.values.LibGlobals2.soundPoolHelper
import com.vankorno.vankornohelpers.LibMisc
import com.vankorno.vankornohelpers.LibSoundPool
import com.vankorno.vankornohelpers.dLog
import com.vankorno.vankornohelpers.values.LibGlobals.androidTestRun
import com.vankorno.vankornohelpers.values.LibGlobals.debugBuild
import com.vankorno.vankornohelpers.values.playSound

private const val TAG = "LibApp"

abstract class LibApp(
    val soundsToInit: Array<Int> = emptyArray()
) : Application() {
    
    protected open fun doOnCreate() {}
    protected open fun dbInit() {}
    
    override fun onCreate() {
        super.onCreate()
        
        androidTestRun = LibMisc().isInstrumentedTestRun()
        
        debugBuild = (applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE) != 0
        
        dbInit()
        
        doOnCreate()
        
        soundInit()
    }
    
    
    private fun soundInit() {
        soundPoolHelper = LibSoundPool(this, soundsToInit)
        playSound = { soundPoolHelper.playSound(it, this) }
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