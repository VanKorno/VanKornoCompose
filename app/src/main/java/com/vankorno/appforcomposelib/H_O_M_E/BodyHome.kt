package com.vankorno.appforcomposelib.H_O_M_E

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.vankorno.appforcomposelib._vm.vm
import com.vankorno.vankornocompose.composables.Spa_______________cer
import com.vankorno.vankornocompose.composables.Spa_______________cerEndScr
import com.vankorno.vankornocompose.composables.barBot.CirclePickerBtn
import com.vankorno.vankornocompose.composables.barBot.CirclePickerBtnSmall
import com.vankorno.vankornocompose.composables.barBot.RoundBottomBtn
import com.vankorno.vankornocompose.composables.barBot.SmallCircleBtn
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
    Spa_______________cer()
    
    TestCircleButtons()
    
    Spa_______________cerEndScr()
}


@Composable
private fun TestCircleButtons() {
    Column {
        Text("Circle buttons", color = Color.White)
        Spa_______________cer(8)
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            RoundBottomBtn(icon = com.vankorno.vankornocompose.values.LibIcon.ArrowLeft, onClick = {})
            SmallCircleBtn(enabled = true, icon = com.vankorno.vankornocompose.values.LibIcon.X, onClick = {})
            CirclePickerBtn(text = "1", enabled = true, onClick = {})
            CirclePickerBtn(text = "2", enabled = false, onClick = {})
            CirclePickerBtnSmall(text = "A", enabled = true, onClick = {})
            CirclePickerBtnSmall(text = "B", enabled = false, onClick = {})
        }
    }
    Spa_______________cer()
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








