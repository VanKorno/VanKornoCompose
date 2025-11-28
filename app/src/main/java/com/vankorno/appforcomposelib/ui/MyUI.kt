package com.vankorno.appforcomposelib.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vankorno.appforcomposelib.popup.PopupMaker
import com.vankorno.vankornocompose.LibMainActivity.Companion.libVm
import com.vankorno.vankornocompose.composables.menu_options.PerforatedTextOption
import com.vankorno.vankornocompose.composables.menu_options.PerforatedToggledVariantPicker
import com.vankorno.vankornocompose.navig.PopStateContextInfo
import com.vankorno.vankornocompose.navig.PopStateOFF
import com.vankorno.vankornocompose.theme_main.LibAccentColor
import com.vankorno.vankornocompose.values.LibGlobals2.currScrFlow
import com.vankorno.vankornocompose.values.MOD_MaxSize
import com.vankorno.vankornocompose.values.MOD_MaxW

@Composable
fun MyUI() {
    val currScr by currScrFlow.collectAsStateWithLifecycle()
    
    ConstraintLayout(
        modifier = MOD_MaxSize
    ) {
        val (body, barTop, barBot, popup) = createRefs()
        
        val popState by libVm.popStateFlow.collectAsStateWithLifecycle()
        
        val modifBody = MOD_MaxSize.constrainAs(body) {
            top.linkTo(barTop.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
        }
        val modifTop = MOD_MaxW.constrainAs(barTop) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        val modifBot = MOD_MaxW
            .constrainAs(barBot) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        val modifPopup = MOD_MaxSize
            .constrainAs(popup) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        
        
        
        
        
        AnimatedVisibility(popState != PopStateOFF) {
            PopupMaker(popState, modifPopup)
        }
    }
}




@Composable
fun HomeBody(
    
) {
    Column(
        MOD_MaxW
            .padding(vertical = 50.dp, horizontal = 10.dp)
            .verticalScroll(rememberScrollState())
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TestPerforatedOptions{ libVm.popState = PopStateContextInfo }
    }
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