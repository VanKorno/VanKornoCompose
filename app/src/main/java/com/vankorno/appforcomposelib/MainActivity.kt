package com.vankorno.appforcomposelib

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.vankorno.vankornocompose.ScrType
import com.vankorno.vankornocompose.ScreenType
import com.vankorno.vankornocompose.composables.MyPopup
import com.vankorno.vankornocompose.composables.menu_options.PerforatedTextOption
import com.vankorno.vankornocompose.composables.menu_options.PerforatedToggledVariantPicker
import com.vankorno.vankornocompose.theme_dynamic.DynamicTheme
import com.vankorno.vankornocompose.values.MOD_MaxSize
import com.vankorno.vankornocompose.values.MOD_MaxW

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        val scrType = ScreenType().calculateScreenType(resources.configuration)
        
        setContent {
            DynamicTheme {
                Surface(
                    MOD_MaxSize,
                    color = MaterialTheme.colorScheme.background
                ) {
                    TestScreen(scrType)
                }
            }
        }
    }
}


@Composable
fun TestScreen(                                                               scrType: ScrType
) {
    var popupON = remember { mutableStateOf(false) }
    
    if (popupON.value) {
        MyPopup(MOD_MaxSize, scrType,
            clickGrayMatter = {
                popupON.value = false
            },
            popupText = "Test text\nMakeRussiaSmallAgain\nMakeAmericaIrrelevantAgain\nMakeUkraineWholeAgain"
        )
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
        isMicroUI = false,
        isSingleColumn = false,
        txt = "Test",
        variantTexts = arrayOf( "one\n kllkjlkj",   "two",
                                "three"                      ),
        
        click = { isON.value = !isON.value },
        
        variantClick = { idx.intValue = it },
        
        longClick = showPopup
    )
    
    PerforatedTextOption(
        isStandalone = true,
        txt = "Test"
    )
    PerforatedTextOption(
        isStandalone = true,
        txt = "Test\nTest"
    )
    PerforatedTextOption(
        isStandalone = true,
        txt = "Test\nTest\nTest"
    )
}







