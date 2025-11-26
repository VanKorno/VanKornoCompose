package com.vankorno.vankornocompose.values

import com.vankorno.vankornocompose.navig.PopState
import com.vankorno.vankornohelpers.lambdaError

var goHome: ()->Unit = { lambdaError("goHome") }
var goBack: ()->Unit = { lambdaError("goBack") }

var popupON: (PopState)->Unit = { lambdaError("popupON") }
var popupOFF: ()->Unit = { lambdaError("popupOFF") }