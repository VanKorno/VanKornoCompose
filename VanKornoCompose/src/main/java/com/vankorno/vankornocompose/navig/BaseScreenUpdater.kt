package com.vankorno.vankornocompose.navig

import com.vankorno.vankornocompose.values.LibGlobals2.libVm
import com.vankorno.vankornohelpers.dLog
import com.vankorno.vankornohelpers.values.LibGlobals.updatingScreen

abstract class BaseScreenUpdater {
    protected val TAG = "ScreenUpdater"
    
    protected open fun onUpdateScreen(currScreen: Screen) {}
    
    internal fun updateScr() {
        updatingScreen = true
        val currScreen = libVm.currScreen.value
        // region LOG
            dLog(TAG, "Updating current screen: $currScreen")
        // endregion
        onUpdateScreen(currScreen)
        updatingScreen = false
    }
}
