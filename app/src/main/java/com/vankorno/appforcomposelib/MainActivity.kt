package com.vankorno.appforcomposelib

import androidx.compose.runtime.Composable
import com.vankorno.appforcomposelib.ui.MyUI
import com.vankorno.vankornocompose.LibMainActivity

class MainActivity : LibMainActivity() {
    
    @Composable
    override fun AppUI() { MyUI() }
    
    override fun beforeStartup() {
        
    }
    
    override fun goingHome() {  }
    
    override fun goingBack() {  }
    
}










