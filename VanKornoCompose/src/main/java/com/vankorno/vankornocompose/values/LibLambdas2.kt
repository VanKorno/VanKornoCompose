package com.vankorno.vankornocompose.values

import com.vankorno.vankornocompose.navig.PopState
import com.vankorno.vankornohelpers.lambdaError

var exitApp: ()->Unit = { lambdaError("exitApp") }

var goHome: ()->Unit = { lambdaError("goHome") }
var goBack: ()->Unit = { lambdaError("goBack") }

var popupON: (PopState)->Unit = { lambdaError("popupON") }
var popupOFF: ()->Unit = { lambdaError("popupOFF") }