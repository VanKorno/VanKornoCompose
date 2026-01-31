package com.vankorno.vankornocompose.vm

import androidx.lifecycle.ViewModel
import com.vankorno.vankornocompose.navig.PopState
import com.vankorno.vankornocompose.navig.PopStateOFF
import com.vankorno.vankornocompose.navig.ScrHome
import com.vankorno.vankornocompose.navig.Screen
import com.vankorno.vankornohelpers.dLog
import com.vankorno.vankornohelpers.values.LibLangConst.LangAuto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

private const val TAG = "LibViewModel"

class LibViewModel() : ViewModel() {
    
    private val _language = MutableStateFlow(LangAuto)
    val langFlow: StateFlow<String> = _language
    var language: String
        get() = _language.value
        set(new) {
            _language.value = new
            // region LOG
                dLog(TAG, "language = $new")
            // endregion
        }
    
    
    private val _currScreen = MutableStateFlow<Screen>(ScrHome)
    val currScreenFlow: StateFlow<Screen> = _currScreen
    var currScreen: Screen
        get() = _currScreen.value
        set(new) {
            _currScreen.value = new
            // region LOG
                dLog(TAG, "currScreen = $new")
            // endregion
        }
    
    
    private val _previousScreen = MutableStateFlow<Screen>(ScrHome)
    val previousScreenFlow: StateFlow<Screen> = _previousScreen
    var previousScreen: Screen
        get() = _previousScreen.value
        set(new) {
            _previousScreen.value = new
            // region LOG
                dLog(TAG, "previousScreen = $new")
            // endregion
        }
    
    
    
    private val _popState = MutableStateFlow<PopState>(PopStateOFF)
    val popStateFlow: StateFlow<PopState> = _popState
    var popState: PopState
        get() = _popState.value
        set(new) {
            _popState.value = new
            // region LOG
                dLog(TAG, "popState = $new")
            // endregion
        }
    
    
}