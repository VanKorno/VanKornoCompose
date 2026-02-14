package com.vankorno.vankornocompose.navig

import com.vankorno.vankornocompose.values.LibGlobals2.ops


object Navig {
    val goTo = ops.exec { scr: Screen -> NavigHooks.goTo(scr) }
    
    val goBack = ops.exec { NavigHooks.goBack() }
    
    val goToStart = ops.exec { NavigHooks.goToStart() }
    
    val updateScreen = ops.exec { NavigHooks.updateScreen() }
}