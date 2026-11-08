package com.vankorno.vankornocompose.composables.barBot

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.vankorno.vankornocompose.ScrMEDIUM
import com.vankorno.vankornocompose.ScrType
import com.vankorno.vankornocompose.actions.tweakTransparency
import com.vankorno.vankornocompose.composables.buttons.RoundBtn
import com.vankorno.vankornocompose.composables.buttons.RoundBtnSmall
import com.vankorno.vankornocompose.dp1
import com.vankorno.vankornocompose.effects.rememberClipboardText
import com.vankorno.vankornocompose.navig.Navig
import com.vankorno.vankornocompose.theme_main.LibColor
import com.vankorno.vankornocompose.values.LibIcon
import com.vankorno.vankornocompose.values.LocalScrType
import com.vankorno.vankornohelpers.values.LibColors.BlackT30
import com.vankorno.vankornohelpers.values.LibLambdas.getClipboard

val RoundBtnBorderW = 3.dp
val SmallRoundBtnBorderW = 2.dp

val MOD_SmallRoundBtnMedium = Modifier.size(47.dp).padding(end = 1.dp)

val MOD_RoundBotBtnMINI = Modifier.size(60.dp).padding(1.dp)
val MOD_RoundBotBtnMEDIUM = Modifier.size(70.dp).padding(2.dp)
val MOD_RoundBotBtnLARGE = Modifier.size(90.dp).padding(2.dp)



@Composable
fun RoundBottomBtn(                                                       icon: Int,
                                                                       icColor: Color = Color.White,
                                                                       enabled: Boolean = true,
                                                                         isBig: Boolean = false,
                                                                       testTag: String = "",
                                                                   onLongClick: ()->Unit = {},
                                                                       onClick: ()->Unit,
) {
    var backgroundColor = Color.Black
    var borderColor = LibColor.BlackSurfBorder.color
    var iconColor = icColor
    
    if (!enabled) {
        backgroundColor = Color(BlackT30)
        borderColor = LibColor.BlackSurfBorderDisabled.color
        iconColor = icColor.tweakTransparency(0.1f)
    }
    
    val scrType = LocalScrType.current
    
    val modifier = if (isBig) {
        if (scrType.size < ScrMEDIUM  ||  scrType == ScrType.LandscapeMedium)
            MOD_RoundBotBtnMEDIUM
        else
            MOD_RoundBotBtnLARGE
    } else {
        if (scrType.size < ScrMEDIUM  ||  scrType == ScrType.LandscapeMedium)
            MOD_RoundBotBtnMINI
        else
            MOD_RoundBotBtnMEDIUM
    }
    
    RoundBtn(
        modifier = modifier,
        backgroundColor = backgroundColor,
        borderColor = borderColor,
        borderWidth = RoundBtnBorderW,
        enabled = enabled,
        testTag = testTag,
        onLongClick = onLongClick,
        onClick = onClick
    ) {
        Icon(
            modifier = Modifier
                .matchParentSize()
                .padding(12.dp1())
            ,
            painter = painterResource(id = icon),
            tint = iconColor,
            contentDescription = null
        )
    }
}



@Composable
fun BackBtn() {
    RoundBottomBtn(
        LibIcon.ArrowBackAndUp,
        onClick = { Navig.goBack.async() },
        onLongClick = { Navig.goToStart.async() }
    )
}


@Composable
fun PasteBtn(                                                          textInField: String,
                                                                           onClick: (String)->Unit,
) {
    val clipboardText by rememberClipboardText()
    
    if (textInField.isEmpty()) {
        RoundBottomBtn(LibIcon.Paste,
            enabled = clipboardText.isNotEmpty(),
            onClick = {
                onClick(getClipboard())
            }
        )
    }
}



@Composable
fun ReorderController(                                                    upEnabled: Boolean,
                                                                            upClick: ()->Unit,
                                                                        downEnabled: Boolean,
                                                                          downClick: ()->Unit,
                                                                        leftEnabled: Boolean,
                                                                          leftClick: ()->Unit,
                                                                       rightEnabled: Boolean,
                                                                         rightClick: ()->Unit,
                                                                        upLongClick: ()->Unit = {},
                                                                      downLongClick: ()->Unit = {},
                                                                      leftLongClick: ()->Unit = {},
                                                                     rightLongClick: ()->Unit = {},
) {
    Row(
        Modifier.padding(top = 20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        RoundBottomBtn(LibIcon.ArrowLeft, enabled=leftEnabled, onClick=leftClick, onLongClick=leftLongClick)
        
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            RoundBottomBtn(LibIcon.ArrowUp, enabled=upEnabled, onClick=upClick, onLongClick=upLongClick)
            RoundBottomBtn(LibIcon.ArrowDown, enabled=downEnabled, onClick=downClick, onLongClick=downLongClick)
        }
        
        RoundBottomBtn(LibIcon.ArrowRight, enabled=rightEnabled, onClick=rightClick, onLongClick=rightLongClick)
    }
}


// TODO Check if needed
@Composable
fun OkCancelBotBtns(                                                           okEnabled: Boolean,
                                                                                 clickOK: ()->Unit,
                                                                             clickCancel: ()->Unit,
) {
    RoundBottomBtn(LibIcon.X, Color.Red,
        isBig = true,
        onClick = clickCancel
    )
    RoundBottomBtn(LibIcon.Check, Color.Green,
        enabled = okEnabled,
        isBig = true,
        onClick = clickOK
    )
}



@Composable
fun SmallClearBtn(                                                                  text: String,
                                                                                 onClick: ()->Unit,
) {
    RoundBtnSmall(
        enabled = text.isNotEmpty(),
        icon = LibIcon.X,
        icColor = Color.Red,
        onClick = onClick
    )
}
