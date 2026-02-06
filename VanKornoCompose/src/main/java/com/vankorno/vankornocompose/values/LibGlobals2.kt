package com.vankorno.vankornocompose.values

import com.vankorno.vankornocompose.vm.LibViewModel
import com.vankorno.vankornodb.api.DbHelper
import com.vankorno.vankornohelpers.LibSoundPool
import com.vankorno.vankornohelpers.dLog
import com.vankorno.vankornohelpers.values.LibGlobals
import com.vankorno.vankornohelpers.values.LibLangConst.LangAuto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object LibGlobals2 {
    private const val TAG = "LibGlobals2"
    
    lateinit var dbh: DbHelper
    
    lateinit var soundPoolHelper: LibSoundPool
    
    lateinit var libVm: LibViewModel
    
    
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
    
    
    
    fun reset(                                                        resetGlobals1: Boolean = true
    ) {
        // region LOG
            dLog(TAG, "reset(resetGlobals1 = $resetGlobals1)")
        // endregion
        if (resetGlobals1)
            LibGlobals.reset()
        
        _language.value = LangAuto
        
    }
}