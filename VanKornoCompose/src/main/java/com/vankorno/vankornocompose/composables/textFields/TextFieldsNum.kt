package com.vankorno.vankornocompose.composables.textFields

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import com.vankorno.vankornocompose.sp1
import com.vankorno.vankornocompose.values.MOD_StandardTextField
import com.vankorno.vankornocompose.vm.VmEvent
import com.vankorno.vankornohelpers.values.LibColors.BlackT30

@Composable
fun LibTextFieldNum(                                              textFieldVal: String,
                                                                        center: Boolean,
                                                                      modifier: Modifier = Modifier,
                                                                 onValueChange: (String)->Unit,
                                                                  focusRequest: VmEvent? = null,
                                                             clearFocusRequest: VmEvent? = null,
                                                                canHaveOneZero: Boolean = false,
) {
    LibStandardTextFieldNum(
        textFieldVal = textFieldVal,
        center = center,
        modifier = modifier,
        fontSize = 26.sp1(),
        hintSize = 18.sp1(),
        onValueChange = onValueChange,
        focusRequest = focusRequest,
        clearFocusRequest = clearFocusRequest,
        canHaveOneZero = canHaveOneZero
    )
}

@Composable
fun LibTextFieldNumSmaller(                                       textFieldVal: String,
                                                                        center: Boolean,
                                                                      modifier: Modifier = Modifier,
                                                                 onValueChange: (String)->Unit,
                                                                  focusRequest: VmEvent? = null,
                                                             clearFocusRequest: VmEvent? = null,
                                                                canHaveOneZero: Boolean = false,
) {
    LibStandardTextFieldNum(
        textFieldVal = textFieldVal,
        center = center,
        modifier = modifier,
        fontSize = 24.sp1(),
        hintSize = 18.sp1(),
        onValueChange = onValueChange,
        focusRequest = focusRequest,
        clearFocusRequest = clearFocusRequest,
        canHaveOneZero = canHaveOneZero
    )
}

@Composable
fun LibStandardTextFieldNum(                                      textFieldVal: String,
                                                                        center: Boolean,
                                                                      modifier: Modifier = Modifier,
                                                                      fontSize: TextUnit,
                                                                      hintSize: TextUnit,
                                                                 onValueChange: (String)->Unit,
                                                                  focusRequest: VmEvent? = null,
                                                             clearFocusRequest: VmEvent? = null,
                                                                canHaveOneZero: Boolean = false,
) {
    LibBasicNumTextField(
        value = textFieldVal,
        onValueChange = onValueChange,
        modifier = modifier,
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
            textAlign = if (center) TextAlign.Center else TextAlign.Start
        ),
        enableFocusHandling = true,
        focusRequest = focusRequest,
        clearFocusRequest = clearFocusRequest,
        canHaveOneZero = canHaveOneZero,
        decorationBox = { innerTextField ->
            Box(
                modifier = MOD_StandardTextField,
                contentAlignment = if (center) Alignment.Center else Alignment.CenterStart
            ) {
                innerTextField()
                HintTextNum(hintSize, textFieldVal)
            }
        }
    )
}


@Composable
private fun HintTextNum(                                                        fontSize: TextUnit,
                                                                         textInTextField: String,
) {
    if (textInTextField.isNotEmpty())  return  //\/\/\/\/\/\
    
    Text(
        "0",
        fontSize = fontSize,
        color = Color(BlackT30)
    )
}