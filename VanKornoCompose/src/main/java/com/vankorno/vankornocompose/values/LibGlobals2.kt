package com.vankorno.vankornocompose.values

import com.vankorno.vankornocompose.navig.ScrHome
import com.vankorno.vankornocompose.navig.Screen
import com.vankorno.vankornohelpers.dLog
import com.vankorno.vankornohelpers.values.LibConstants.LangAuto
import com.vankorno.vankornohelpers.values.LibGlobals
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object LibGlobals2 {
    private const val TAG = "LibGlobals2"
    
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
    
    
    private val _currScr = MutableStateFlow<Screen>(ScrHome)
    val currScrFlow: StateFlow<Screen> = _currScr
    var currScr: Screen
        get() = _currScr.value
        set(new) {
            _currScr.value = new
            // region LOG
                dLog(TAG, "currScr = $new")
            // endregion
        }
    
    @Volatile var previousScr: Screen = ScrHome
    
    
    
    fun reset(                                                        resetGlobals1: Boolean = true
    ) {
        // region LOG
            dLog(TAG, "reset(resetGlobals1 = $resetGlobals1)")
        // endregion
        if (resetGlobals1)
            LibGlobals.reset()
        
        _language.value = LangAuto
        
        _currScr.value = ScrHome
        previousScr = ScrHome
        
        
    }
}