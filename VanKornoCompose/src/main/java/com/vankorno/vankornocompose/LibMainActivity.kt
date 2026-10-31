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
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import com.vankorno.vankornocompose.db.LibDbVals.TTTMisc
import com.vankorno.vankornocompose.launcher.LibAppLauncher.beforeFirstLaunch
import com.vankorno.vankornocompose.launcher.LibAppLauncher.beforeNotFirstLaunch
import com.vankorno.vankornocompose.navig.Navig
import com.vankorno.vankornocompose.navig.PopupOFF
import com.vankorno.vankornocompose.theme_main.LibColor
import com.vankorno.vankornocompose.theme_main.LibMainScaffold
import com.vankorno.vankornocompose.values.LibGlobals2.libVm
import com.vankorno.vankornocompose.values.LibLambdas2.exitApp
import com.vankorno.vankornocompose.values.LibLambdas2.popupOFF
import com.vankorno.vankornocompose.values.LibLambdas2.popupON
import com.vankorno.vankornocompose.vm.LibViewModel
import com.vankorno.vankornodb.api.DbRuntime.dbh
import com.vankorno.vankornohelpers.LibMisc
import com.vankorno.vankornohelpers.clipboard.LibClipboard
import com.vankorno.vankornohelpers.dLog
import com.vankorno.vankornohelpers.values.LibColors.PlainBlack
import com.vankorno.vankornohelpers.values.LibGlobals.actExists
import com.vankorno.vankornohelpers.values.LibGlobals.actPaused
import com.vankorno.vankornohelpers.values.LibGlobals.actRunning
import com.vankorno.vankornohelpers.values.LibGlobals.appStarted
import com.vankorno.vankornohelpers.values.LibGlobals.configChangeJustHappened
import com.vankorno.vankornohelpers.values.LibLambdas.getClipboard
import com.vankorno.vankornohelpers.values.LibLambdas.hideKeyboard
import com.vankorno.vankornohelpers.values.LibLambdas.longToast
import com.vankorno.vankornohelpers.values.LibLambdas.minimizeApp
import com.vankorno.vankornohelpers.values.LibLambdas.setClipboard
import com.vankorno.vankornohelpers.values.LibLambdas.shortToast

private const val TAG = "MAIN Act."

abstract class LibMainActivity(                val statusBarColor: Color = LibColor.BlackSurf.color,
                                                val underAppColor: Int = PlainBlack,
                                                   val typography: Typography = Typography(),
                                            val usesMinuteUpdater: Boolean = true,
) : ComponentActivity() {
    
    private lateinit var minUpdateReceiver: BroadcastReceiver
    
    override fun onCreate(                                            savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        // region LOG
            dLog(TAG, "onCreate()")
        // endregion
        actExists = true
        
        enableEdgeToEdge()
        
        LibScreen().scrConfig(this, underAppColor)
        
        libVm = ViewModelProvider(this, SavedStateViewModelFactory(application, this))[LibViewModel::class.java]
        
        initLibLambdas()
        
        beforeStartup()
        startup(savedInstanceState)
        afterStartup()
        
        setBackBtn()
        
        setContent {
            LibMainScaffold(statusBarColor, typography) {
                AppUI()
            }
        }
        
        afterCompose()
    }
    
    @Composable
    protected abstract fun AppUI()
    
    protected open fun beforeStartup() {}
    
    protected open fun startupFirstLaunch() {}
    protected open fun startupNotFirstLaunch() {}
    protected open fun startupLaunch() {}
    
    protected open fun startupConfigChange() {}
    
    protected open fun startupAfterProcessDeath() {
        startupNotFirstLaunch()
        startupLaunch()
    }
    
    
    private fun startup(                                              savedInstanceState: Bundle?
    ) {
        when {
            savedInstanceState == null -> {
                // region LOG
                    dLog(TAG, "startup(): Full startup logic runs...")
                // endregion
                val miscTableExists = dbh.tableExists(TTTMisc)
                
                if (!miscTableExists) {
                    beforeFirstLaunch()
                    // region LOG
                        dLog(TAG, "startupFirstLaunch()")
                    // endregion
                    startupFirstLaunch()
                } else {
                    beforeNotFirstLaunch()
                    // region LOG
                        dLog(TAG, "startupFirstLaunch()")
                    // endregion
                    startupNotFirstLaunch()
                }
                // region LOG
                    dLog(TAG, "startupLaunch()")
                // endregion
                startupLaunch()
            }
            configChangeJustHappened -> {
                // region LOG
                    dLog(TAG, "startup(): Config change logic runs...")
                // endregion
                startupConfigChange()
            }
            else -> {
                // region LOG
                    dLog(TAG, "startup(): Starting after process death...")
                // endregion
                startupAfterProcessDeath()
            }
        }
        configChangeJustHappened = false // Just in case
        appStarted = true
    }
    protected open fun afterStartup() {}
    
    protected open fun afterCompose() {}
    
    protected open fun beforePausing() {}
    protected open fun afterPausing() {}
    
    protected open fun beforeStart() {}
    protected open fun afterStart() {}
    
    protected open fun beforeStop() {}
    protected open fun afterStop() {}
    
    protected open fun beforeDestroy() {}
    protected open fun afterDestroy() {}
    
    protected open fun onResumeAfterPause() {}
    
    protected open fun beforeResuming() {}
    protected open fun afterResuming() {}
    
    protected open fun doEveryMinute() {}
    
    protected open fun beforePopupOFF() {}
    protected open fun afterPopupOFF() {}
    
    protected open fun doBeforeMinimizingApp() {}
    
    protected open fun doBeforeForceExit() {}
    
    
    
    private fun initLibLambdas() {
        minimizeApp = {
            // region LOG
                dLog(TAG, "minimizeApp()")
            // endregion
            doBeforeMinimizingApp()
            finishAffinity()
        }
        exitApp = {
            // region LOG
                dLog(TAG, "exitApp()")
            // endregion
            doBeforeForceExit()
            finishAndRemoveTask()
        }
        
        popupON = {
            hideKeyboard()
            libVm.popupState.value = it
        }
        popupOFF = {
            beforePopupOFF()
            hideKeyboard()
            libVm.popupState.value = PopupOFF
            afterPopupOFF()
        }
        
        longToast = { LibMisc().makeToast(this, it, Toast.LENGTH_LONG) }
        shortToast = { LibMisc().makeToast(this, it, Toast.LENGTH_SHORT) }
        
        getClipboard = { LibClipboard.getClipboard(this) }
        setClipboard = { LibClipboard.setClipboard(this, it) }
    }
    
    
    
    
    private fun setBackBtn() {
        onBackPressedDispatcher.addCallback(this) { Navig.goBack.async() }
    }
    
    
    override fun onPause() {
        // region LOG
            dLog(TAG, "onPause()")
        // endregion
        beforePausing()
        
        super.onPause()
        
        if (usesMinuteUpdater)
            unregisterReceiver(minUpdateReceiver)
        
        actRunning = false
        actPaused = true
        
        afterPausing()
    }
    
    override fun onStart() {
        beforeStart()
        super.onStart()
        afterStart()
    }
    
    override fun onStop() {
        // region LOG
            dLog(TAG, "onStop()")
        // endregion
        beforeStop()
        
        super.onStop()
        actRunning = false
        
        afterStop()
    }
    
    
    override fun onDestroy() {
        // region LOG
            dLog(TAG, "onDestroy()")
        // endregion
        beforeDestroy()
        
        super.onDestroy()
        
        actExists = false
        configChangeJustHappened = isChangingConfigurations
        
        afterDestroy()
    }
    
    
    override fun onResume() {
        // region LOG
            dLog(TAG, "onResume()")
        // endregion
        beforeResuming()
        
        super.onResume()
        
        if (usesMinuteUpdater)
            startMinUpdater()
        
        if (actPaused)
            onResumeAfterPause()
        
        actRunning = true
        actPaused = false
        
        afterResuming()
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
