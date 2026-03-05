package com.vankorno.appforcomposelib._vm

import com.vankorno.vankornocompose.vm.VmHolder

object vm : VmHolder() {
    lateinit var a: VmMain
    
    override fun onInit() {
        a = get()
    }
}