package com.vankorno.vankornocompose.view_model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.vankorno.vankornocompose.ScrType
import com.vankorno.vankornohelpers.values.LibConstants.LangAuto
import com.vankorno.vankornohelpers.values.LibConstants.iOFF
import com.vankorno.vankornohelpers.values.LibGlobals
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

private enum class Key {
    popupState,
    language
}

class LibVm(                                         private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    
    private val _scrType = MutableStateFlow(ScrType.PortraitSmall)
    val scrType: StateFlow<ScrType> = _scrType
    fun updateScreenType(scrType: ScrType) { _scrType.value = scrType }
    
    
    private val _popupState = MutableStateFlow(savedStateHandle[Key.popupState.name] ?: iOFF)
    val popupState: StateFlow<Int> = _popupState
    fun setPopupState(                                                               new: Int
    ) {
        _popupState.value = new
        savedStateHandle[Key.popupState.name] = new
    }
    
    
    private val _language = MutableStateFlow(savedStateHandle[Key.language.name] ?: LangAuto)
    val language: StateFlow<String> = _language
    fun setLang(                                                                     new: String
    ) {
        _language.value = new 
        LibGlobals.language = new
        savedStateHandle[Key.language.name] = new
    }
    
    
}