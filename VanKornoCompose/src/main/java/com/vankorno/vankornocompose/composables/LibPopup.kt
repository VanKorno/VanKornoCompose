package com.vankorno.vankornocompose.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.vankorno.vankornocompose.LibScreen.Companion.microUI
import com.vankorno.vankornocompose.LibScreen.Companion.smallUI
import com.vankorno.vankornocompose.ScrType
import com.vankorno.vankornocompose.dp4
import com.vankorno.vankornocompose.dp5
import com.vankorno.vankornocompose.theme_main.LibColor
import com.vankorno.vankornocompose.values.MOD_W90
import com.vankorno.vankornohelpers.values.hideKeyboard


@Composable
fun GreyPopup(                                           modifier: Modifier,
                                                          scrType: ScrType,
                                                       clickScrim: ()->Unit,
                                                      composables: @Composable ColumnScope.()->Unit,
) {
    LibPopup(modifier, scrType, 
        LibColor.BlackScrim.color,
        LibColor.Surface.color,
        clickScrim,
        composables
    )
}



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LibPopup(                                            modifier: Modifier,
                                                          scrType: ScrType,
                                                       scrimColor: Color,
                                                        cardColor: Color,
                                                       clickScrim: ()->Unit,
                                                      composables: @Composable ColumnScope.()->Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    
    Row(
        modifier
            .background(color = scrimColor)
            .combinedClickable(
                enabled = true,
                onClick = clickScrim,
                onLongClick = clickScrim,
                role = Role.Button,
                interactionSource = interactionSource,
                indication = null
            )
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        PopupCard(scrType, cardColor, composables)
    }
}


@Composable
private fun PopupCard(                                    scrType: ScrType,
                                                        cardColor: Color,
                                                      composables: @Composable ColumnScope.()->Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    
    val paddBot =   if (microUI)
                        20.dp
                    else if (smallUI)
                        40.dp
                    else
                        55.dp
    
    val modifier =  if (scrType == ScrType.LandscapeMedium  ||  scrType == ScrType.LandscapeLarge)
                        Modifier.widthIn(min = 300.dp, max = 600.dp)
                    else
                        MOD_W90
    Column(
        modifier
            .padding(top = 5.dp5(), bottom = paddBot)
            .background(
                color = cardColor,
                shape = RoundedCornerShape(16.dp)
            )
            .verticalScroll(rememberScrollState())
            .clickable(
                onClick = hideKeyboard,
                interactionSource = interactionSource,
                indication = null
            )
            .padding(
                vertical = if (smallUI  ||  scrType == ScrType.LandscapeMedium)  8.dp  else  16.dp,
                horizontal = 8.dp4()
            )
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        composables()
    }
}














