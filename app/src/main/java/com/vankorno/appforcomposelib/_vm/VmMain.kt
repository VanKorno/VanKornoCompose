package com.vankorno.appforcomposelib._vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.vankorno.vankornocompose.vm.VmText
import com.vankorno.vankornocompose.vm.VmTextNum

class VmMain(                                                                 ssh: SavedStateHandle
) : ViewModel() {
    
    val input1 = VmText(ssh, "input1")
    
    val inputNum1 = VmTextNum(ssh, "inputNum1")
    
    val inputSingleDigit = VmTextNum(ssh, "inputSingleDigit", maxLength = 1)
    
}