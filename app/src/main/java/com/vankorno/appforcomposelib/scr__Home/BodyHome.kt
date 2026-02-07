package com.vankorno.appforcomposelib.scr__Home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.vankorno.vankornocompose.composables.menu_options.PerforatedTextOption
import com.vankorno.vankornocompose.composables.menu_options.PerforatedToggledVariantPicker
import com.vankorno.vankornocompose.navig.PopupContextInfo
import com.vankorno.vankornocompose.theme_main.LibAccentColor
import com.vankorno.vankornocompose.values.LibGlobals2.libVm


@Composable
fun BodyHome() {
    TestPerforatedOptions { libVm.popupState.value = PopupContextInfo }
}




@Composable
fun TestPerforatedOptions(                                                     showPopup: ()->Unit
) {
    val isON = remember { mutableStateOf(true) }
    val idx = remember { mutableIntStateOf(0) }
    
    PerforatedToggledVariantPicker(
        isON = isON.value,
        chosenIdx = idx.intValue,
        isSingleColumn = false,
        txt = "Test",
        color = LibAccentColor.Green.color,
        variantTexts = arrayOf( "one\n kllkjlkj",   "two",
                                "three"                      ),
        
        click = { isON.value = !isON.value },
        
        variantClick = { idx.intValue = it },
        
        longClick = showPopup
    )
    
    PerforatedTextOption(
        isStandalone = true,
        txt = "Test",
        color = LibAccentColor.Violet.color,
    )
    PerforatedTextOption(
        isStandalone = true,
        txt = "Test\nTest",
        color = LibAccentColor.Yellow.color,
    )
    PerforatedTextOption(
        isStandalone = true,
        txt = "Test\nTest\nTest",
    )
}