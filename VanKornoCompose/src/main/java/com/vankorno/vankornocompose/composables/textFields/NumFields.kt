package com.vankorno.vankornocompose.composables.textFields

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import com.vankorno.vankornocompose.sp1
import com.vankorno.vankornocompose.vm.VmTextNum

@Composable
fun LibTextFieldNum(                                                    vmText: VmTextNum,
                                                                        center: Boolean,
                                                                      modifier: Modifier = Modifier,
                                                                canHaveOneZero: Boolean = false,
) {
    LibStandardTextFieldNum(
        vmText = vmText,
        center = center,
        modifier = modifier,
        fontSize = 26.sp1(),
        canHaveOneZero = canHaveOneZero
    )
}


@Composable
fun LibTextFieldNumSmaller(                                             vmText: VmTextNum,
                                                                        center: Boolean,
                                                                      modifier: Modifier = Modifier,
                                                                canHaveOneZero: Boolean = false,
) {
    LibStandardTextFieldNum(
        vmText = vmText,
        center = center,
        modifier = modifier,
        fontSize = 24.sp1(),
        canHaveOneZero = canHaveOneZero
    )
}


@Composable
fun LibStandardTextFieldNum(                                            vmText: VmTextNum,
                                                                        center: Boolean,
                                                                      modifier: Modifier = Modifier,
                                                                      fontSize: TextUnit,
                                                                canHaveOneZero: Boolean = false,
) {
    LibBasicNumTextField(
        vmText = vmText,
        modifier = modifier,
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
            textAlign = if (center) TextAlign.Center else TextAlign.Start
        ),
        canHaveOneZero = canHaveOneZero,
        enableFocusHandling = true,
        contentAlignment = if (center) Alignment.Center else Alignment.CenterStart,
    )
}


