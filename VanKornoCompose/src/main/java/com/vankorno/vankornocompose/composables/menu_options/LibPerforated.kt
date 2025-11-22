package com.vankorno.vankornocompose.composables.menu_options

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vankorno.vankornocompose.LibScreen.Companion.microUI
import com.vankorno.vankornocompose.dp1
import com.vankorno.vankornocompose.dp2
import com.vankorno.vankornocompose.dp4
import com.vankorno.vankornocompose.sp1
import com.vankorno.vankornocompose.theme_main.LibColor
import com.vankorno.vankornocompose.values.MOD_MaxW
import com.vankorno.vankornocompose.values.MOD_W50
import com.vankorno.vankornohelpers.LibUI

private val PerforatedCorner = 10.dp
private val PerforatedPaddV = 8.dp
private val PerforatedPaddH = 3.dp
private val MOD_Perforated =  Modifier
                                .widthIn(max = 600.dp)
                                .fillMaxWidth()


@Composable
fun PerforatedToggledVariantPicker(                         isON: Boolean,
                                                       chosenIdx: Int,
                                                  isSingleColumn: Boolean,
                                                             txt: String,
                                                    variantTexts: Array<String>,
                                                           color: Color = LibColor.Surface.color,
                                                      separColor: Color = LibColor.Background.color,
                                                           click: ()->Unit,
                                                    variantClick: (Int)->Unit,
                                                       longClick: ()->Unit = {},
) {
    PerforatedToggleOption(isON, false, txt, color, click, longClick)
    
    if (isON) {
        PerforatedVariantPicker(chosenIdx, isSingleColumn, variantTexts, color, separColor, variantClick, longClick)
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PerforatedToggleOption(                                    isON: Boolean,
                                                       isStandalone: Boolean,
                                                                txt: String,
                                                              color: Color = LibColor.Surface.color,
                                                              click: ()->Unit,
                                                          longClick: ()->Unit = {},
) {
    val interactionSource = remember { MutableInteractionSource() }
    
    val paddV = if (microUI)
                    PerforatedPaddV - 2.dp
                else
                    PerforatedPaddV
    
    var botPadd = 0.dp
    var botCorner = 0.dp
    
    if (!isON || isStandalone) {
        botCorner = PerforatedCorner
        botPadd = paddV
    }
    
    Row(
       MOD_Perforated
            .padding(top = paddV, start = PerforatedPaddH, end = PerforatedPaddH, bottom = botPadd)
            .background(
                color = color,
                shape = RoundedCornerShape(
                    topStart = PerforatedCorner,
                    topEnd = PerforatedCorner,
                    bottomStart = botCorner,
                    bottomEnd = botCorner
                )
            )
            .combinedClickable(
                enabled = true,
                onClick = click,
                onLongClick = longClick,
                role = Role.Button,
                interactionSource = interactionSource,
                indication = null
            )
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .padding(
                    vertical = 10.dp2(),
                    horizontal = 8.dp2()
                )
                .size(32.dp4())
            ,
            painter = painterResource(id = LibUI().getCheckBoxIcon(isON)),
            tint = LibColor.WhiteText.color,
            contentDescription = null
        )
        
        Text(
            modifier = Modifier.padding(
                vertical = 5.dp2(),
                horizontal = 4.dp1()
            ),
            text = txt,
            fontSize = 18.sp1(),
            textAlign = TextAlign.Start,
            maxLines = 6,
            color = LibColor.WhiteText.color
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PerforatedTextOption(                              isStandalone: Boolean,
                                                                txt: String,
                                                              color: Color = LibColor.Surface.color,
                                                              click: ()->Unit = {},
                                                          longClick: ()->Unit = {},
) {
    val interactionSource = remember { MutableInteractionSource() }
    
    var botPadd = 0.dp
    var botCorner = 0.dp
    
    if (isStandalone) {
        botPadd = PerforatedPaddV
        botCorner = PerforatedCorner
    }
    
    Row(
        MOD_Perforated
            .heightIn(min = 65.dp)
            .padding(top = PerforatedPaddV, start = PerforatedPaddH, end = PerforatedPaddH, bottom = botPadd)
            .background(
                color = color,
                shape = RoundedCornerShape(
                    topStart = PerforatedCorner,
                    topEnd = PerforatedCorner,
                    bottomStart = botCorner,
                    bottomEnd = botCorner
                )
            )
            .combinedClickable(
                enabled = true,
                onClick = click,
                onLongClick = longClick,
                role = Role.Button,
                interactionSource = interactionSource,
                indication = null
            )
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = txt,
            fontSize = 18.sp1(),
            textAlign = TextAlign.Center,
            maxLines = 12,
            color = LibColor.WhiteText.color,
        )
    }
}



// When it has two columns, it's better to logically organize elements horizontally. Even idx - first col, odd - second

@Composable
fun PerforatedVariantPicker(                           chosenIdx: Int,
                                                  isSingleColumn: Boolean,
                                                    variantTexts: Array<String>,
                                                           color: Color = LibColor.Surface.color,
                                                      separColor: Color = LibColor.Background.color,
                                                           click: (Int)->Unit,
                                                       longClick: ()->Unit = {},
) {
    Column (
        MOD_Perforated
            .padding(bottom = PerforatedPaddV, start = PerforatedPaddH, end = PerforatedPaddH)
            .background(
                color = color,
                shape = RoundedCornerShape(bottomStart = PerforatedCorner, bottomEnd = PerforatedCorner)
            )
        ,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val separShape = RoundedCornerShape(size = 3.dp)
        
        val modifSeparH = Modifier
            .fillMaxHeight(0.90f)
            .width(4.dp)
            .background(separColor, separShape)
        
        val modifSeparV = Modifier
            .fillMaxWidth(0.94f)
            .height(4.dp)
            .background(separColor, separShape)
        
        if (microUI || isSingleColumn) {
            val modif = MOD_MaxW
                .fillMaxHeight()
                .padding(
                    vertical = 6.dp2(),
                    horizontal = 5.dp2()
                )
            
            Box(modifSeparV) {}
            
            for (idx in variantTexts.indices) {
                VariantBtn(chosenIdx, idx, modif, variantTexts[idx], click, longClick)
            }
        }
        else {
            val modif1 = MOD_W50.fillMaxHeight().padding(vertical = 5.dp, horizontal = 3.dp)
            val modif2 = MOD_MaxW.fillMaxHeight().padding(vertical = 5.dp, horizontal = 3.dp)
            
            for (idx in variantTexts.indices) {
                if (idx % 2 == 0) {
                    Box(modifSeparV) {}
                    
                    Row(
                        Modifier
                            .fillMaxWidth(0.94f)
                            .height(IntrinsicSize.Max)
                        ,
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        VariantBtn(chosenIdx, idx, modif1, variantTexts[idx], click, longClick)
                        Box(modifSeparH) {}
                        
                        if (idx != variantTexts.lastIndex) {
                            VariantBtn(chosenIdx, idx+1, modif2, variantTexts[idx+1], click, longClick)
                        }
                    }
                    
                }
            }
        }
        
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun VariantBtn(                                                   chosenIdx: Int,
                                                                            currIdx: Int,
                                                                           modifier: Modifier,
                                                                                txt: String,
                                                                              click: (Int)->Unit,
                                                                          longClick: ()->Unit = {},
) {
    val interactionSource = remember { MutableInteractionSource() }
    
    val isChosen = chosenIdx == currIdx
    
    Row (
        modifier
            .combinedClickable(
                enabled = !isChosen,
                onClick = { click(currIdx) },
                onLongClick = longClick,
                role = Role.Button,
                interactionSource = interactionSource,
                indication = null
            )
        ,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val color = LibColor.WhiteText.color
        
        Icon(
            modifier = Modifier.padding(vertical = 2.dp, horizontal = 8.dp),
            painter = painterResource(id = LibUI().getCircleIcon(isChosen)),
            tint = color,
            contentDescription = null
        )
        
        Text(
            modifier = MOD_MaxW.padding(horizontal = 4.dp),
            text = txt,
            fontSize = 16.sp1(),
            textAlign = TextAlign.Start,
            maxLines = 2,
            color = color
        )
    }
}










