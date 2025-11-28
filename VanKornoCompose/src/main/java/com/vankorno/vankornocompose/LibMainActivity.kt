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
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import com.vankorno.vankornocompose.navig.PopStateOFF
import com.vankorno.vankornocompose.theme_main.LibColor
import com.vankorno.vankornocompose.theme_main.LibMainScaffold
import com.vankorno.vankornocompose.values.exitApp
import com.vankorno.vankornocompose.values.goBack
import com.vankorno.vankornocompose.values.goHome
import com.vankorno.vankornocompose.values.popupOFF
import com.vankorno.vankornocompose.values.popupON
import com.vankorno.vankornocompose.vm.LibViewModel
import com.vankorno.vankornohelpers.LibMisc
import com.vankorno.vankornohelpers.clipboard.LibClipboard
import com.vankorno.vankornohelpers.dLog
import com.vankorno.vankornohelpers.values.LibColors.PlainBlack
import com.vankorno.vankornohelpers.values.LibGlobals.actExists
import com.vankorno.vankornohelpers.values.LibGlobals.actPaused
import com.vankorno.vankornohelpers.values.LibGlobals.actRunning
import com.vankorno.vankornohelpers.values.LibGlobals.appStarted
import com.vankorno.vankornohelpers.values.getClipboard
import com.vankorno.vankornohelpers.values.hideKeyboard
import com.vankorno.vankornohelpers.values.longToast
import com.vankorno.vankornohelpers.values.minimizeApp
import com.vankorno.vankornohelpers.values.setClipboard
import com.vankorno.vankornohelpers.values.shortToast

private const val TAG = "MAIN Act."

abstract class LibMainActivity(                val statusBarColor: Color = LibColor.BlackSurf.color,
                                                val underAppColor: Int = PlainBlack,
                                                   val typography: Typography = Typography(),
                                            val usesMinuteUpdater: Boolean = true,
) : ComponentActivity() {
    companion object {
        lateinit var libVm: LibViewModel
    }
    private lateinit var minUpdateReceiver: BroadcastReceiver
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        // region LOG
            dLog(TAG, "onCreate()")
        // endregion
        actExists = true
        
        enableEdgeToEdge()
        
        LibScreen().scrConfig(this, underAppColor)
        
        initLibLambdas()
        
        libVm = ViewModelProvider(this)[LibViewModel::class.java]
        
        beforeStartup()
        startup()
        afterStartup()
        
        setBackBtn()
        
        setContent {
            LibMainScaffold(statusBarColor, typography) {
                AppUI()
            }
        }
    }
    
    @Composable
    protected abstract fun AppUI()
    
    protected abstract fun goingHome()
    protected abstract fun goingBack()
    
    
    protected open fun beforeStartup() {}
    
    protected open fun isEssentialDataMissing(): Boolean = false
    protected open fun startupFirstLaunch() {}
    protected open fun startupConfigChange() {}
    
    private fun startup() {
        if (!appStarted || isEssentialDataMissing()) {
            // region LOG
                dLog(TAG, "startup(): Full startup logic runs (not config change)...")
            // endregion
            startupFirstLaunch()
        } else {
            // region LOG
                dLog(TAG, "startup(): Config change logic runs...")
            // endregion
            startupConfigChange()
        }
    }
    protected open fun afterStartup() {}
    
    
    protected open fun doOnPause() {}
    protected open fun doOnStart() {}
    protected open fun doOnStop() {}
    protected open fun doOnDestroy() {}
    protected open fun doOnResumeAfterPause() {}
    protected open fun doOnResumeAfterAll() {}
    
    protected open fun doEveryMinute() {}
    
    protected open fun doAfterPopupOFF() {}
    protected open fun doBeforeMinimizingApp() {}
    protected open fun doBeforeForceExit() {}
    
    
    
    private fun initLibLambdas() {
        minimizeApp = {
            doBeforeMinimizingApp()
            finishAffinity()
        }
        exitApp = {
            doBeforeForceExit()
            finishAndRemoveTask()
        }
        
        popupON = {
            hideKeyboard()
            libVm.popState = it
        }
        popupOFF = {
            hideKeyboard()
            libVm.popState = PopStateOFF
            doAfterPopupOFF()
        }
        
        longToast = { LibMisc().makeToast(this, it, Toast.LENGTH_LONG) }
        shortToast = { LibMisc().makeToast(this, it, Toast.LENGTH_SHORT) }
        
        getClipboard = { LibClipboard.getClipboard(this) }
        setClipboard = { LibClipboard.setClipboard(this, it) }
        
        goHome = { goingHome() }
        goBack = { goingBack() }
    }
    
    
    
    
    private fun setBackBtn() {
        onBackPressedDispatcher.addCallback(this) { goBack() }
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
    
    override fun onStart() {
        super.onStart()
        doOnStart()
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