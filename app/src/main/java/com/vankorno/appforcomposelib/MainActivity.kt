package com.vankorno.appforcomposelib

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vankorno.vankornocompose.LibMainActivity
import com.vankorno.vankornocompose.LibScreen.Companion.scrType
import com.vankorno.vankornocompose.composables.GreyPopup
import com.vankorno.vankornocompose.composables.menu_options.PerforatedTextOption
import com.vankorno.vankornocompose.composables.menu_options.PerforatedToggledVariantPicker
import com.vankorno.vankornocompose.sp1
import com.vankorno.vankornocompose.theme_main.LibAccentColor
import com.vankorno.vankornocompose.theme_main.LibColor
import com.vankorno.vankornocompose.values.MOD_MaxSize
import com.vankorno.vankornocompose.values.MOD_MaxW
import com.vankorno.vankornocompose.values.MOD_W90

class MainActivity : LibMainActivity() {
    
    override fun beforeStartup() {
        
    }
    
    @Composable
    override fun AppUI() {
        TestScreen()
    }
    
    override fun goingHome() {
        
    }
    
    override fun goingBack() {
        
    }
    
}


@Composable
fun TestScreen() {
    var popupON = remember { mutableStateOf(false) }
    
    if (popupON.value) {
        GreyPopup(
            MOD_MaxSize, scrType,
            clickScrim = {
                popupON.value = false
            }
        ) {
            Text(
                modifier = MOD_W90
                     .padding(vertical = 10.dp)
                ,
                text = "Test text\n" +
                "MaterialTheme.colorScheme.onSecondaryContainer horizontalAlignment = Alignment.CenterHorizontally",
                fontSize = 18.sp1(),
                textAlign = TextAlign.Start,
                color = LibColor.WhiteText.color
            )
        }
        return //\/\/\/\/\/\
    }
    
    Column(
        MOD_MaxW
            .padding(vertical = 50.dp, horizontal = 10.dp)
            .verticalScroll(rememberScrollState())
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TestPerforatedOptions{ popupON.value = true }
    }
}


@Composable
fun TestPerforatedOptions(                                                     showPopup: ()->Unit
) {
    var isON = remember { mutableStateOf(true) }
    var idx = remember { mutableIntStateOf(0) }
    
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







