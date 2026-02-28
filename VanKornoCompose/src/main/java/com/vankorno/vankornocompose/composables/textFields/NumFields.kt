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
import com.vankorno.vankornocompose.values.MOD_MaxW
import com.vankorno.vankornocompose.vm.VmTextNum


@Composable
fun WhiteNumFieldSmall(                                                 vmText: VmTextNum,
                                                                      modifier: Modifier = Modifier,
                                                                        center: Boolean = true,
                                                                canHaveOneZero: Boolean = false,
) {
    WhiteNumField(
        vmText = vmText,
        modifier = modifier,
        fontSize = 24.sp1(),
        center = center,
        canHaveOneZero = canHaveOneZero
    )
}


@Composable
fun WhiteNumField(                                                      vmText: VmTextNum,
                                                                      modifier: Modifier = MOD_MaxW,
                                                                      fontSize: TextUnit = 26.sp1(),
                                                                        center: Boolean = true,
                                                                canHaveOneZero: Boolean = false,
) {
    LibBasicNumField(
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


