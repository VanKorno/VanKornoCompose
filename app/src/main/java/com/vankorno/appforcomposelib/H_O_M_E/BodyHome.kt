package com.vankorno.appforcomposelib.H_O_M_E

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.vankorno.appforcomposelib._vm.vm
import com.vankorno.vankornocompose.composables.Spa_______________cer
import com.vankorno.vankornocompose.composables.Spa_______________cerEndScr
import com.vankorno.vankornocompose.composables.menu_options.PerforatedTextOption
import com.vankorno.vankornocompose.composables.menu_options.PerforatedToggledVariantPicker
import com.vankorno.vankornocompose.composables.textFields.WhiteMultilineField
import com.vankorno.vankornocompose.composables.textFields.WhiteNumField
import com.vankorno.vankornocompose.composables.textFields.WhiteTextField
import com.vankorno.vankornocompose.navig.PopupContextInfo
import com.vankorno.vankornocompose.theme_main.LibAccentColor
import com.vankorno.vankornocompose.values.LibGlobals2.libVm
import com.vankorno.vankornocompose.values.LibGlobals2.ops

@Composable
fun BodyHome() {
    TestPerforatedOptions { libVm.popupState.value = PopupContextInfo }
    Spa_______________cer()
    
    TestTextFields()
    
    Spa_______________cerEndScr()
}


@Composable
fun TestPerforatedOptions(                                                     showPopup: ()->Unit
) {
    val isON = remember { mutableStateOf(true) }
    val idx = remember { mutableIntStateOf(0) }
    
    val flip = ops.exec {
        isON.value = !isON.value
    }
    
    PerforatedToggledVariantPicker(
        isON = isON.value,
        chosenIdx = idx.intValue,
        isSingleColumn = false,
        txt = "Test",
        color = LibAccentColor.Green.color,
        variantTexts = arrayOf("one\n kllkjlkj", "two", "three"),
        
        click = { flip.async() },
        
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


@Composable
private fun TestTextFields() {
    OneTextField("Single line") {
        WhiteTextField(vm.a.inputSingleLine)
    }
    OneTextField("Multiline") {
        WhiteMultilineField(vm.a.input1)
    }
    OneTextField("Number") {
         WhiteNumField(vm.a.inputNum1)
    }
    OneTextField("Single number") {
        WhiteNumField(vm.a.inputSingleDigit)
    }
}


@Composable
private fun OneTextField(                                               title: String,
                                                                      content: @Composable ()->Unit,
) {
    Text(title, color = Color.White)
    content()
    Spa_______________cer()
}








