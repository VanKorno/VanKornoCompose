package com.vankorno.vankornocompose.navig

import com.vankorno.vankornocompose.values.LibGlobals2.libVm
import com.vankorno.vankornohelpers.hasUI


fun atHome() = hasUI() && libVm.currScreen.value == ScrHome


fun withHomeScr(                                                                     run: ()->Unit
) {
    if (atHome()) {
        run()
    }
}