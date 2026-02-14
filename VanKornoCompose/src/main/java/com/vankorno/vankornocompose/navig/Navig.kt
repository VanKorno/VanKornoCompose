package com.vankorno.vankornocompose.navig

import com.vankorno.vankornocompose.values.LibGlobals2.ops
import com.vankorno.vankornohelpers.lambdaError

object Navig {
    private var _goTo: (Screen) -> Unit = { lambdaError("goTo") }
    private var _goBack: () -> Unit = { lambdaError("goBack") }
    private var _goToStart: () -> Unit = { lambdaError("goToStart") }
    private var _updateScreen: () -> Unit = { lambdaError("updateScreen") }
    
    private var initialized = false
    
    fun init(                                                                 goTo: (Screen)->Unit,
                                                                            goBack: ()->Unit,
                                                                         goToStart: ()->Unit,
                                                                      updateScreen: ()->Unit,
    ) {
        if (initialized) error("Navig already initialized")

        _goTo = goTo
        _goBack = goBack
        _goToStart = goToStart
        _updateScreen = updateScreen
        
        initialized = true
    }

    val goTo = ops.exec { scr: Screen -> _goTo(scr) }
    val goBack = ops.exec { _goBack() }
    val goToStart = ops.exec { _goToStart() }
    val updateScreen = ops.exec { _updateScreen() }
}