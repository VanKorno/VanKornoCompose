package com.vankorno.vankornocompose.composables.textFields

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import com.vankorno.vankornocompose.sp1
import com.vankorno.vankornocompose.values.MOD_MaxW
import com.vankorno.vankornocompose.vm.VmText

@Composable
fun WhiteTextField(                                                     vmText: VmText,
                                                                      modifier: Modifier = MOD_MaxW,
                                                                      fontSize: TextUnit = 26.sp1(),
                                                                        center: Boolean = false,
                                                                    capitalize: Boolean = true,
                                                                   autoCorrect: Boolean = true,
) = WhiteMultilineField(
    vmText = vmText,
    center = center,
    modifier = modifier,
    fontSize = fontSize,
    lineQuantRange = 1..1,
    capitalize = capitalize,
    autoCorrect = autoCorrect
)


@Composable
fun WhiteMultilineField(                                        vmText: VmText,
                                                              modifier: Modifier = MOD_MaxW,
                                                              fontSize: TextUnit = 26.sp1(),
                                                        lineQuantRange: IntRange = 1..Int.MAX_VALUE,
                                                                center: Boolean = false,
                                                            capitalize: Boolean = true,
                                                           autoCorrect: Boolean = true,
) {
    LibBasicTextField(
        vmText = vmText,
        modifier = modifier,
        contentAlignment = if (center) Alignment.Center else Alignment.CenterStart,
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
            textAlign = if (center)  TextAlign.Center  else  TextAlign.Start
        ),
        lineQuantRange = lineQuantRange,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            capitalization = if (capitalize) KeyboardCapitalization.Sentences else KeyboardCapitalization.None,
            autoCorrectEnabled = autoCorrect
        ),
    )
}










