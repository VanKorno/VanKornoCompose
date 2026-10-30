package com.vankorno.vankornocompose.navig

import com.vankorno.vankornocompose.values.LibGlobals2.libVm
import com.vankorno.vankornohelpers.dLog
import com.vankorno.vankornohelpers.values.LibGlobals.updatingScreen

abstract class BaseScreenUpdater {
    protected val TAG = "ScreenUpdater"
    
    protected open fun onUpdateScreen() {}
    
    internal fun updateScr() {
        updatingScreen = true
        // region LOG
            dLog("UpdateScr", "update(). Curr.screen: ${libVm.currScreen.value}")
        // endregion
        onUpdateScreen()
        updatingScreen = false
    }
}
