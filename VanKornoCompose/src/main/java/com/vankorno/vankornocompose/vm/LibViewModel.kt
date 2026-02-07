package com.vankorno.vankornocompose.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.vankorno.vankornocompose.navig.PopupOFF
import com.vankorno.vankornocompose.navig.PopupState
import com.vankorno.vankornocompose.navig.ScrHome
import com.vankorno.vankornocompose.navig.Screen
import com.vankorno.vankornohelpers.dLog

private const val TAG = "LibViewModel"

class LibViewModel(                                                           ssh: SavedStateHandle
) : ViewModel() {
    
    val currScreen = VmSavedVal<Screen>(ssh, "currScreen", ScrHome) { new ->
        // region LOG
            dLog(TAG, "currScreen = $new")
        // endregion
    }
    
    val previousScreen = VmSavedVal<Screen>(ssh, "previousScreen", ScrHome) { new ->
        // region LOG
            dLog(TAG, "previousScreen = $new")
        // endregion
    }
    
    val popupState = VmSavedVal<PopupState>(ssh, "popupState", PopupOFF) { new ->
        // region LOG
            dLog(TAG, "popupState = $new")
        // endregion
    }
    
}