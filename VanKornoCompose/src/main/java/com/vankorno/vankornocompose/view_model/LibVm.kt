package com.vankorno.vankornocompose.view_model

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.vankorno.vankornocompose.ScrType
import com.vankorno.vankornohelpers.values.LibConstants.LangAuto
import com.vankorno.vankornohelpers.values.LibConstants.ScreenMain
import com.vankorno.vankornohelpers.values.LibConstants.iOFF
import com.vankorno.vankornohelpers.values.LibGlobals
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LibVm(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val KEY_currScr = "1"
    val KEY_previousScr = "2"
    val KEY_popupState = "3"
    val KEY_language = "4"
    
    private val _scrType = MutableStateFlow(ScrType.PortraitSmall)
    val scrType: StateFlow<ScrType> = _scrType
    fun updateScreenType(scrType: ScrType) { _scrType.value = scrType }
    
    
    private val _currScr = MutableStateFlow(savedStateHandle[KEY_currScr] ?: ScreenMain)
    val currScr: StateFlow<Int> = _currScr
    fun setCurrScr(                                                                  new: Int
    ) {
        _currScr.value = new
        LibGlobals.currScreen = new
        savedStateHandle[KEY_currScr] = new
        // region LOG
        Log.d("LibVm", "Current screen: $new")
        // endregion
    }
    
    private val _previousScr = MutableStateFlow(savedStateHandle[KEY_previousScr] ?: ScreenMain)
    val previousScr: StateFlow<Int> = _previousScr
    fun setPrevScr(                                                                  new: Int
    ) {
        _previousScr.value = new
        savedStateHandle[KEY_previousScr] = new
        // region LOG
        Log.d("LibVm", "Previous screen: $new")
        // endregion
    }
    
    
    
    
    
    
    private val _popupState = MutableStateFlow(savedStateHandle[KEY_popupState] ?: iOFF)
    val popupState: StateFlow<Int> = _popupState
    fun setPopupState(                                                               new: Int
    ) {
        _popupState.value = new
        savedStateHandle[KEY_popupState] = new
    }
    
    
    private val _language = MutableStateFlow(savedStateHandle[KEY_language] ?: LangAuto)
    val language: StateFlow<String> = _language
    fun setLang(                                                                     new: String
    ) {
        _language.value = new 
        LibGlobals.language = new
        savedStateHandle[KEY_language] = new
    }
    
    
}