package com.vankorno.vankornocompose.navig

import com.vankorno.vankornocompose.values.LibGlobals2.libVm
import com.vankorno.vankornohelpers.dLog
import com.vankorno.vankornohelpers.values.LibGlobals.navigating
import com.vankorno.vankornohelpers.values.LibLambdas.hideKeyboard

abstract class BaseNavigator {
    protected val TAG = "Navigator"
    
    open fun onGoToScreen(                                              targetScr: Screen = ScrHome,
                                                                 autoHideKeyboard: Boolean = true,
    ) {
        navigating = true
        // region LOG
            dLog(TAG, "goToScreen(targetScr = $targetScr)")
        // endregion
        
        if (autoHideKeyboard)
            hideKeyboard()
        
        val previousScr = libVm.currScreen.value
        libVm.previousScreen.value = previousScr
        // region LOG
            dLog(TAG, "previousScr = $previousScr)")
        // endregion
        
        // region LOG
            dLog(TAG, "specialBackstack()")
        // endregion
        specialBackstack(targetScr)
        
        // region LOG
            dLog(TAG, "runBeforeGoing()")
        // endregion
        runBeforeGoing(targetScr)
        
        libVm.currScreen.value = targetScr
        // region LOG
            dLog(TAG, "previousScr = $previousScr)")
        // endregion
        
        // region LOG
            dLog(TAG, "runAfterGoing()")
        // endregion
        runAfterGoing(targetScr)
        
        navigating = false
        // region LOG
            dLog(TAG, "goToScreen(): Navigation complete. Current screen = ${libVm.currScreen.value})")
        // endregion
    }
    
    
    open fun onGoToStart() {
        onGoToScreen(ScrHome)
    }
    
    
    protected open fun specialBackstack(targetScr: Screen) {}
    
    protected open fun runBeforeGoing(targetScr: Screen) {}
    
    protected open fun runAfterGoing(targetScr: Screen) {}
    
}
