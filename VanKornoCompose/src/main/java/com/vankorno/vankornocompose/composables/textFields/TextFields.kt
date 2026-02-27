package com.vankorno.vankornocompose.composables.textFields

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import com.vankorno.vankornocompose.sp1
import com.vankorno.vankornocompose.values.MOD_MaxW
import com.vankorno.vankornocompose.values.MOD_StandardTextField

@Composable
fun LibTextField(                                              textFieldVal: TextFieldValue,
                                                                     center: Boolean,
                                                                   modifier: Modifier = MOD_MaxW,
                                                                   fontSize: TextUnit = 26.sp1(),
                                                              onValueChange: (TextFieldValue)->Unit,
) = LibTextFieldBase(textFieldVal, center, modifier, fontSize,
    onValueChange = onValueChange
)


@Composable
fun LibTextFieldBase(                                          textFieldVal: TextFieldValue,
                                                                     center: Boolean,
                                                                   modifier: Modifier = MOD_MaxW,
                                                                   fontSize: TextUnit,
                                                                 capitalize: Boolean = true,
                                                                autoCorrect: Boolean = true,
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
                Row(
                    modifier = MOD_StandardTextField,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = if (center)  Arrangement.Center  else  Arrangement.Start
                ) {
                    innerTextField()
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = if (capitalize) KeyboardCapitalization.Sentences else KeyboardCapitalization.None,
                autoCorrect = autoCorrect
            ),
        )
    }
}




@Composable
fun LibTextFieldLong(                                          textFieldVal: TextFieldValue,
                                                                   modifier: Modifier = MOD_MaxW,
                                                                   fontSize: TextUnit = 26.sp1(),
                                                              onValueChange: (TextFieldValue)->Unit,
) = LibTextFieldBase(
        textFieldVal = textFieldVal,
        center = false,
        modifier = modifier,
        fontSize = fontSize,
        capitalize = true,
        autoCorrect = true,
        onValueChange = onValueChange
)








