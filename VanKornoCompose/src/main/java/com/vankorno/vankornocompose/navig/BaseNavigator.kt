package com.vankorno.vankornocompose.navig

import com.vankorno.vankornocompose.values.LibGlobals2.libVm
import com.vankorno.vankornohelpers.dLog
import com.vankorno.vankornohelpers.values.LibGlobals.navigating
import com.vankorno.vankornohelpers.values.LibLambdas.hideKeyboard

abstract class BaseNavigator {
    protected val TAG = "Navigator"
    
    open fun onGoToScreen(                                           targetScreen: Screen = ScrHome,
                                                                 autoHideKeyboard: Boolean = true,
    ) {
        navigating = true
        // region LOG
            dLog(TAG, "goToScreen(targetScr = $targetScreen)")
        // endregion
        
        if (autoHideKeyboard)
            hideKeyboard()
        
        val previousScreen = libVm.currScreen.value
        libVm.previousScreen.value = previousScreen
        // region LOG
            dLog(TAG, "previousScr = $previousScreen)")
        // endregion
        
        // region LOG
            dLog(TAG, "specialBackstack()")
        // endregion
        specialBackstack(previousScreen, targetScreen)
        
        // region LOG
            dLog(TAG, "runBeforeGoing()")
        // endregion
        runBeforeGoing(previousScreen, targetScreen)
        
        libVm.currScreen.value = targetScreen
        // region LOG
            dLog(TAG, "previousScr = $previousScreen)")
        // endregion
        
        // region LOG
            dLog(TAG, "runAfterGoing()")
        // endregion
        runAfterGoing(previousScreen, targetScreen)
        
        navigating = false
        // region LOG
            dLog(TAG, "goToScreen(): Navigation complete. Current screen = ${libVm.currScreen.value})")
        // endregion
    }
    
    
    open fun onGoToStart() {
        onGoToScreen(ScrHome)
    }
    
    
    protected open fun specialBackstack(previousScreen: Screen, targetScreen: Screen) {}
    
    protected open fun runBeforeGoing(previousScreen: Screen, targetScreen: Screen) {}
    
    protected open fun runAfterGoing(previousScreen: Screen, targetScreen: Screen) {}
    
}
