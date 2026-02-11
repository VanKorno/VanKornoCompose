package com.vankorno.vankornocompose.navig

import com.vankorno.vankornohelpers.lambdaError

internal object NavigHooks {
    var goTo: (Screen)->Unit = { lambdaError("goTo") }
    var goBack: ()->Unit = { lambdaError("goBack") }
    var goRoot: ()->Unit = { lambdaError("goRoot") }
    var updateScreen: ()->Unit = { lambdaError("updateScreen") }
}
