package com.vankorno.vankornocompose.composables.textFields

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import com.vankorno.vankornocompose.sp1
import com.vankorno.vankornocompose.values.MOD_StandardTextField
import com.vankorno.vankornohelpers.values.LibColors.BlackT30

@Composable
fun LibTextFieldNum(                                           textFieldVal: TextFieldValue,
                                                                     center: Boolean,
                                                                   modifier: Modifier,
                                                              onValueChange: (TextFieldValue)->Unit,
) {
    LibBaseTextFieldNum(
        textFieldVal = textFieldVal,
        center = center,
        modifier = modifier,
        fontSize = 26.sp1(),
        hintSize = 18.sp1(),
        onValueChange = onValueChange,
    )
}


@Composable
fun LibTextFieldNumSmaller(                                    textFieldVal: TextFieldValue,
                                                                     center: Boolean,
                                                                   modifier: Modifier,
                                                              onValueChange: (TextFieldValue)->Unit,
) {
    LibBaseTextFieldNum(
        textFieldVal = textFieldVal,
        center = center,
        modifier = modifier,
        fontSize = 24.sp1(),
        hintSize = 18.sp1(),
        onValueChange = onValueChange,
    )
}


@Composable
fun LibBaseTextFieldNum(                                       textFieldVal: TextFieldValue,
                                                                     center: Boolean,
                                                                   modifier: Modifier,
                                                                   fontSize: TextUnit,
                                                                   hintSize: TextUnit,
                                                              onValueChange: (TextFieldValue)->Unit,
) {
    SelectionContainer {
        BasicTextField(
            modifier = modifier,
            value = textFieldVal,
            onValueChange = onValueChange,
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = fontSize,
                fontWeight = FontWeight.Bold,
                textAlign = if (center)  TextAlign.Center  else  TextAlign.Start
            ),
            singleLine = true,
            decorationBox = { innerTextField ->
                Box(
                    modifier = MOD_StandardTextField,
                    contentAlignment = if (center)  Alignment.Center  else  Alignment.CenterStart
                ) {
                    innerTextField()
                    HintTextNum(hintSize, textFieldVal.text)
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
        )
    }
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