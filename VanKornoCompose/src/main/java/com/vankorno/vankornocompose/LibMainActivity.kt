package com.vankorno.vankornocompose

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vankorno.vankornocompose.LibScreen.Companion.scrTypeFlow
import com.vankorno.vankornocompose.theme_main.LibMainScaffold
import com.vankorno.vankornocompose.values.LocalLanguage
import com.vankorno.vankornocompose.values.LocalScrType
import com.vankorno.vankornohelpers.LibClipBoard
import com.vankorno.vankornohelpers.LibMisc
import com.vankorno.vankornohelpers.dLog
import com.vankorno.vankornohelpers.values.LibGlobals.actExists
import com.vankorno.vankornohelpers.values.LibGlobals.actPaused
import com.vankorno.vankornohelpers.values.LibGlobals.actRunning
import com.vankorno.vankornohelpers.values.LibGlobals.langFlow
import com.vankorno.vankornohelpers.values.getBuffer
import com.vankorno.vankornohelpers.values.longToast
import com.vankorno.vankornohelpers.values.minimizeApp
import com.vankorno.vankornohelpers.values.setBuffer
import com.vankorno.vankornohelpers.values.shortToast


private const val TAG = "MAIN Act."


abstract class LibMainActivity(                              val usesMinuteUpdater: Boolean = true
) : ComponentActivity() {
    
    private lateinit var minUpdateReceiver: BroadcastReceiver
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        // region LOG
            dLog(TAG, "onCreate()")
        // endregion
        actExists = true
        
        enableEdgeToEdge()
        
        LibScreen().scrConfig(this, -0x1000000)
        
        initLibLambdas()
        
        appLogic()
        
        setBackBtn()
        
        setContent {
            LibMainScaffold {
                val lang by langFlow.collectAsStateWithLifecycle()
                val scrType by scrTypeFlow.collectAsStateWithLifecycle()
                
                CompositionLocalProvider(
                    LocalLanguage provides lang,
                    LocalScrType provides scrType
                ) {
                    AppUI()
                }
            }
        }
    }
    
    @Composable
    protected abstract fun AppUI()
    
    protected open fun appLogic() {}
    
    protected open fun doOnPause() {}
    protected open fun doOnStop() {}
    protected open fun doOnDestroy() {}
    protected open fun doOnResumeAfterPause() {}
    protected open fun doOnResumeAfterAll() {}
    
    protected open fun doEveryMinute() {}
    
    protected open fun doOnBackPressed() {}
    
    
    
    private fun initLibLambdas() {
        minimizeApp = { finishAffinity() }
        
        longToast = { LibMisc().makeToast(this, it, Toast.LENGTH_LONG) }
        shortToast = { LibMisc().makeToast(this, it, Toast.LENGTH_SHORT) }
        
        getBuffer = { LibClipBoard().getTxt(this) }
        setBuffer = { LibClipBoard().setTxt(this, it) }
    }
    
    
    private fun setBackBtn() {
        onBackPressedDispatcher.addCallback(this) { doOnBackPressed() }
    }
    
    
    override fun onPause() {
        super.onPause()
        // region LOG
            dLog(TAG, "onPause()")
        // endregion
        if (usesMinuteUpdater)
            unregisterReceiver(minUpdateReceiver)
        
        actRunning = false
        actPaused = true
        
        doOnPause()
    }
    
    
    override fun onStop() {
        super.onStop()
        // region LOG
            dLog(TAG, "onStop()")
        // endregion
        actRunning = false
        
        doOnStop()
    }
    
    
    override fun onDestroy() {
        super.onDestroy()
        // region LOG
            dLog(TAG, "onDestroy()")
        // endregion
        actExists = false
        
        doOnDestroy()
    }
    
    
    override fun onResume() {
        super.onResume()
        // region LOG
            dLog(TAG, "onResume()")
        // endregion
        
        if (usesMinuteUpdater)
            startMinUpdater()
        
        if (actPaused) {
            doOnResumeAfterPause()
        }
        actRunning = true
        actPaused = false
        
        doOnResumeAfterAll()
    }
    
    
    private fun startMinUpdater() {
        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_TIME_TICK)
        minUpdateReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                doEveryMinute()
            }
        }
        registerReceiver(minUpdateReceiver, intentFilter)
    }
    
    
}