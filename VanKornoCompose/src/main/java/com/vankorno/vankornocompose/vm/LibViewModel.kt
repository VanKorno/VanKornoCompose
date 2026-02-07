package com.vankorno.vankornocompose.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.vankorno.vankornocompose.navig.PopState
import com.vankorno.vankornocompose.navig.PopStateOFF
import com.vankorno.vankornocompose.navig.ScrHome
import com.vankorno.vankornocompose.navig.Screen
import com.vankorno.vankornohelpers.dLog

private const val TAG = "LibViewModel"

class LibViewModel(                                                      ssHandle: SavedStateHandle
) : ViewModel() {
    
    val currScreen = VmSavedVal<Screen>(ssHandle, "currScreen", ScrHome) { new ->
        // region LOG
            dLog(TAG, "currScreen = $new")
        // endregion
    }
    
    val previousScreen = VmSavedVal<Screen>(ssHandle, "previousScreen", ScrHome) { new ->
        // region LOG
            dLog(TAG, "previousScreen = $new")
        // endregion
    }
    
    val popupState = VmSavedVal<PopState>(ssHandle, "popupState", PopStateOFF) { new ->
        // region LOG
            dLog(TAG, "popupState = $new")
        // endregion
    }
    
}