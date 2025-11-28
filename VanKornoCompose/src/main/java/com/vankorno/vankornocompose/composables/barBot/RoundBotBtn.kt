package com.vankorno.vankornocompose.composables.barBot

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vankorno.vankornocompose.LibScreen.Companion.smallUI
import com.vankorno.vankornocompose.ScrMEDIUM
import com.vankorno.vankornocompose.ScrType
import com.vankorno.vankornocompose.actions.applyIf
import com.vankorno.vankornocompose.actions.tweakTransparency
import com.vankorno.vankornocompose.dp1
import com.vankorno.vankornocompose.effects.rememberClipboardText
import com.vankorno.vankornocompose.sp1
import com.vankorno.vankornocompose.theme_main.LibColor
import com.vankorno.vankornocompose.values.LibIcon
import com.vankorno.vankornocompose.values.LocalScrType
import com.vankorno.vankornocompose.values.goBack
import com.vankorno.vankornocompose.values.goHome
import com.vankorno.vankornohelpers.values.LibColors.*
import com.vankorno.vankornohelpers.values.getClipboard

val RoundBtnBorderW = 3.dp
val SmallRoundBtnBorderW = 2.dp

val MOD_SmallRoundBtnMini = Modifier.size(37.dp).padding(end = 1.dp)
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
                                                                       onClick: ()->Unit,
) = RoundBottomBtn(icon, icColor, enabled, isBig, testTag, onClick){}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RoundBottomBtn(                                                       icon: Int,
                                                                       icColor: Color = Color.White,
                                                                       enabled: Boolean = true,
                                                                         isBig: Boolean = false,
                                                                       testTag: String = "",
                                                                       onClick: ()->Unit,
                                                                   onLongClick: ()->Unit,
) {
    var backgroundColor = Color.Black
    var borderColor =  LibColor.BlackSurfBorder.color
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
    
    val interactionSource = remember { MutableInteractionSource() }
    
    Box(
        modifier
            .background(backgroundColor, CircleShape)
            .border(RoundBtnBorderW, borderColor, CircleShape)
            .combinedClickable(
                enabled = enabled,
                onClick = onClick,
                onLongClick = onLongClick,
                interactionSource = interactionSource,
                indication = null
            )
            .applyIf(testTag.isNotBlank()) {
                testTag(testTag)
            }
        ,
        contentAlignment = Alignment.Center
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
        onClick = goBack,
        onLongClick = goHome
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







@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CirclePickerBtn(                                                          txt: String,
                                                                          enabled: Boolean,
                                                                            color: Int = PlainBlack,
                                                                      borderColor: Int = GreyText,
                                                                          onClick: ()->Unit,
) {
    var backgroundColor = Color.Transparent
    var borderCol = Color.Transparent
    if (enabled) {
        backgroundColor = Color(color)
        borderCol =  Color(borderColor)
    }
    val modifier = if (smallUI) MOD_SmallRoundBtnMini  else  MOD_SmallRoundBtnMedium
    
    val interactionSource = remember { MutableInteractionSource() }
    
    Box(
        modifier
            .background(backgroundColor, CircleShape)
            .border(SmallRoundBtnBorderW, borderCol, CircleShape)
            .combinedClickable(
                onClick = onClick,
                interactionSource = interactionSource,
                indication = null
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = txt,
            color = Color.White,
            fontSize = 16.sp1(),
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            textAlign = TextAlign.Center
        )
    }
}



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SmallCircleBtn(                                                    enabled: Boolean,
                                                                          icon: Int,
                                                                       icColor: Color = Color.White,
                                                                       onClick: ()->Unit,
) {
    var backgroundColor = Color.Black
    var borderColor =  LibColor.BlackSurfBorder.color
    var iconColor = icColor
    
    if (!enabled) {
        backgroundColor = Color(BlackT30)
        borderColor = LibColor.BlackSurfBorderDisabled.color
        iconColor = icColor.tweakTransparency(0.1f)
    }
    
    val modifier = MOD_SmallRoundBtnMedium
    
    val interactionSource = remember { MutableInteractionSource() }
    
    Box(
        modifier
            .background(backgroundColor, CircleShape)
            .border(SmallRoundBtnBorderW, borderColor, CircleShape)
            .combinedClickable(
                onClick = onClick,
                interactionSource = interactionSource,
                indication = null
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .matchParentSize()
                .padding(7.dp),
            painter = painterResource(id = icon),
            tint = iconColor,
            contentDescription = null
        )
    }
}

@Composable
fun SmallClearBtn(                                                                   txt: String,
                                                                                 onClick: ()->Unit,
) {
    SmallCircleBtn(
        enabled = txt.isNotEmpty(),
        icon = LibIcon.X,
        icColor = Color.Red,
        onClick = onClick
    )
}