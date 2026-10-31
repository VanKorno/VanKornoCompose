package com.vankorno.vankornocompose.navig

import com.vankorno.vankornocompose.values.LibGlobals2.libVm
import com.vankorno.vankornohelpers.dLog
import com.vankorno.vankornohelpers.values.LibGlobals.navigatingBack

abstract class BaseBackNavigator {
    protected val TAG = "BackNavig"
    
    open fun onGoBack() {
        navigatingBack = true
        // region LOG
            dLog(TAG, "goBack()")
        // endregion
        
        if (libVm.popupState.value != PopupOFF)
            popup(libVm.popupState.value)
        else
            noPopup()
        
        // region LOG
            dLog(TAG, "Back-navigation complete.")
        // endregion
        navigatingBack = false
    }
    
    
    protected open fun onBackFromPopup(popupState: PopupState) {}
    
    private fun popup(                                                        popupState: PopupState
    ) {
        // region LOG
            dLog(TAG, "Going back from a popup ($popupState)")
        // endregion
        onBackFromPopup(popupState)
    }
    
    
    protected open fun onBackWithoutPopup() {}
    private fun noPopup() {
        onBackWithoutPopup()
    }
    
}
