package com.vankorno.vankornocompose.values

import com.vankorno.vankornocompose.vm.LibViewModel
import com.vankorno.vankornocompose.vm.VmVal
import com.vankorno.vankornodb.api.DbHelper
import com.vankorno.vankornohelpers.LibSoundPool
import com.vankorno.vankornohelpers.dLog
import com.vankorno.vankornohelpers.values.LibGlobals
import com.vankorno.vankornohelpers.values.LibLangConst.LangAuto

object LibGlobals2 {
    private const val TAG = "LibGlobals2"
    
    lateinit var dbh: DbHelper
    
    lateinit var soundPoolHelper: LibSoundPool
    
    lateinit var libVm: LibViewModel
    
    
    val language = VmVal(LangAuto) { new ->
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
        
        language.value = LangAuto
        
    }
}