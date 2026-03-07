package com.vankorno.vankornocompose.values

import com.vankorno.vankornocompose.navig.PopupState
import com.vankorno.vankornohelpers.lambdaError

object LibLambdas2 {
    var exitApp: ()->Unit = { lambdaError("exitApp") }
    
    var popupON: (PopupState)->Unit = { lambdaError("popupON") }
    var popupOFF: ()->Unit = { lambdaError("popupOFF") }
}








