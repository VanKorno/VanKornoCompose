package com.vankorno.appforcomposelib.H_O_M_E

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import com.vankorno.appforcomposelib._vm.vm
import com.vankorno.vankornocompose.composables.Spa_______________cer
import com.vankorno.vankornocompose.composables.Spa_______________cerEndScr
import com.vankorno.vankornocompose.composables.Spa_______________cerH
import com.vankorno.vankornocompose.composables.barBot.RoundBottomBtn
import com.vankorno.vankornocompose.composables.buttons.RoundBtnSmall
import com.vankorno.vankornocompose.composables.buttons.RoundPickerBtn
import com.vankorno.vankornocompose.composables.buttons.RoundPickerBtnSmall
import com.vankorno.vankornocompose.composables.menu_options.PerforatedTextOption
import com.vankorno.vankornocompose.composables.menu_options.PerforatedToggledVariantPicker
import com.vankorno.vankornocompose.composables.textFields.WhiteMultilineField
import com.vankorno.vankornocompose.composables.textFields.WhiteNumField
import com.vankorno.vankornocompose.composables.textFields.WhiteTextField
import com.vankorno.vankornocompose.navig.PopupContextInfo
import com.vankorno.vankornocompose.theme_main.LibAccentColor
import com.vankorno.vankornocompose.values.LibGlobals2.libVm
import com.vankorno.vankornocompose.values.LibGlobals2.ops
import com.vankorno.vankornocompose.values.LibIcon
import com.vankorno.vankornocompose.values.MOD_MaxW

@Composable
fun BodyHome() {
    TestPerforatedOptions { libVm.popupState.value = PopupContextInfo }
    Spa_______________cer()
    
    TestTextFields()
    Spa_______________cer()
    
    TestRoundButtons()
    
    Spa_______________cerEndScr()
}


@Composable
private fun TestRoundButtons() {
    Column {
        Text("Round buttons", color = Color.White)
        Spa_______________cer(8)
        RoundButtonDemoRow("Round bottom button") {
            RoundBottomBtn(icon = LibIcon.ArrowLeft, onClick = {})
        }
        RoundButtonDemoRow("Small round button, enabled") {
            RoundBtnSmall(enabled = true, icon = LibIcon.X, onClick = {})
        }
        RoundButtonDemoRow("Picker, picked") {
            RoundPickerBtn(text = "1", picked = true, onClick = {})
        }
        RoundButtonDemoRow("Picker, unpicked") {
            RoundPickerBtn(text = "2", picked = false, onClick = {})
        }
        RoundButtonDemoRow("Small picker, picked") {
            RoundPickerBtnSmall(text = "A", picked = true, onClick = {})
        }
        RoundButtonDemoRow("Small picker, unpicked") {
            RoundPickerBtnSmall(text = "B", picked = false, onClick = {})
        }
    }
    Spa_______________cer()
}


@Composable
private fun RoundButtonDemoRow(
    label: String,
    button: @Composable () -> Unit
) {
    Row(
        MOD_MaxW,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        button()
        Spa_______________cerH(10)
        Text(label, color = Color.White)
    }
}


@Composable
fun TestPerforatedOptions(                                                     showPopup: ()->Unit
) {
    val isON = remember { mutableStateOf(true) }
    val idx = remember { mutableIntStateOf(0) }
    
    val flip = ops.op {
        isON.value = !isON.value
    }
    
    PerforatedToggledVariantPicker(
        isON = isON.value,
        chosenIdx = idx.intValue,
        isSingleColumn = false,
        text = "Test",
        color = LibAccentColor.Green.color,
        variantTexts = arrayOf("one\n kllkjlkj", "two", "three"),
        
        onClick = { flip.async() },
        
        variantClick = { idx.intValue = it },
        
        onLongClick = showPopup
    )
    
    PerforatedTextOption(
        isStandalone = true,
        text = "Test",
        color = LibAccentColor.Violet.color,
    )
    PerforatedTextOption(
        isStandalone = true,
        text = "Test\nTest",
        color = LibAccentColor.Yellow.color,
    )
    PerforatedTextOption(
        isStandalone = true,
        text = "Test\nTest\nTest",
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








