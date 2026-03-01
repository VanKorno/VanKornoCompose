package com.vankorno.appforcomposelib

import androidx.compose.runtime.Composable
import com.vankorno.appforcomposelib._ui.MyUI
import com.vankorno.appforcomposelib._vm.vm
import com.vankorno.vankornocompose.LibMainActivity

class MainActivity : LibMainActivity() {
    
    @Composable
    override fun AppUI() { MyUI() }
    
    override fun beforeStartup() {
        vm.init(this, application)
    }
    
    
}










