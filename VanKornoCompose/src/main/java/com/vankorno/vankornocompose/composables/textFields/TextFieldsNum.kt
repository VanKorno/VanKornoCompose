package com.vankorno.vankornocompose.composables.textFields

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import com.vankorno.vankornocompose.sp1
import com.vankorno.vankornocompose.vm.VmEvent

@Composable
fun LibTextFieldNum(                                                  value: TextFieldValue,
                                                                     center: Boolean,
                                                                   modifier: Modifier = Modifier,
                                                              onValueChange: (TextFieldValue)->Unit,
                                                               focusRequest: VmEvent? = null,
                                                          clearFocusRequest: VmEvent? = null,
                                                             canHaveOneZero: Boolean = false,
) {
    LibStandardTextFieldNum(
        value = value,
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
fun LibTextFieldNumSmaller(                                           value: TextFieldValue,
                                                                     center: Boolean,
                                                                   modifier: Modifier = Modifier,
                                                              onValueChange: (TextFieldValue)->Unit,
                                                               focusRequest: VmEvent? = null,
                                                          clearFocusRequest: VmEvent? = null,
                                                             canHaveOneZero: Boolean = false,
) {
    LibStandardTextFieldNum(
        value = value,
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
fun LibStandardTextFieldNum(                                          value: TextFieldValue,
                                                                     center: Boolean,
                                                                   modifier: Modifier = Modifier,
                                                                   fontSize: TextUnit,
                                                                   hintSize: TextUnit,
                                                              onValueChange: (TextFieldValue)->Unit,
                                                               focusRequest: VmEvent? = null,
                                                          clearFocusRequest: VmEvent? = null,
                                                             canHaveOneZero: Boolean = false,
) {
    LibBasicNumTextField(
        value = value,
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
    )
}


