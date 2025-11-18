package com.vankorno.vankornocompose.vm

import androidx.lifecycle.ViewModel
import com.vankorno.vankornocompose.navig.PopState
import com.vankorno.vankornocompose.navig.PopStateOFF
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LibViewModel() : ViewModel() {
    
    private val _popState = MutableStateFlow<PopState>(PopStateOFF)
    val popStateFlow: StateFlow<PopState> = _popState
    var popState: PopState
        get() = _popState.value
        set(new) {
            _popState.value = new 
        }
    
    
}