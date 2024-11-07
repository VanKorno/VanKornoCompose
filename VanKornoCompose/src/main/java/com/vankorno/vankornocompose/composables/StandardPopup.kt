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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vankorno.vankornocompose.ScrType
import com.vankorno.vankornocompose.ScreenType
import com.vankorno.vankornocompose.actions.tweakTransparency
import com.vankorno.vankornocompose.values.MOD_W90


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyPopup(                                        modifier: Modifier,
                                                     scrType: ScrType,
                                             clickGrayMatter: ()->Unit,
                                                   popupText: String = "",
                                                  otherParts: @Composable ColumnScope.()->Unit = {}
) {
    val interactionSource by remember { mutableStateOf(MutableInteractionSource()) }
    
    Row(
        modifier
            .background(
                color = MaterialTheme.colorScheme.background.tweakTransparency(0.9f)
            )
            .combinedClickable(
                enabled = true,
                onClick = clickGrayMatter,
                onLongClick = clickGrayMatter,
                role = Role.Button,
                interactionSource = interactionSource,
                indication = null
            )
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        PopupCard(scrType, popupText, otherParts)
    }
}


@Composable
fun PopupCard(                                            scrType: ScrType,
                                                              txt: String,
                                                       otherParts: @Composable ColumnScope.()->Unit
) {
    val scrCl = ScreenType()
    val isSmallUI = scrCl.isSmallUI(scrType)
    val isMicroUI = scrCl.isMicroUI(scrType)
    
    var pTop = 20.dp
    var pBot = 55.dp
    var pTxt = 18.dp
    
    if (isSmallUI) {
        pTop = 10.dp
        pBot = 40.dp
        pTxt = 10.dp
    } else if (isMicroUI) {
        pTop = 5.dp
        pBot = 20.dp
        pTxt = 5.dp
    }
    val interactionSource by remember { mutableStateOf(MutableInteractionSource()) }
    
    val modifier =  if (scrType == ScrType.LandscapeMedium  ||  scrType == ScrType.LandscapeLarge)
                        Modifier.widthIn(min = 300.dp, max = 600.dp)
                    else
                        MOD_W90
    
    val keyboard = LocalSoftwareKeyboardController.current
    val hideKeyboard: ()->Unit = { keyboard?.hide() }
    
    Column(
        modifier
            .padding(top = pTop, bottom = pBot)
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = RoundedCornerShape(12.dp)
            )
            .verticalScroll(rememberScrollState())
            .clickable(
                onClick = hideKeyboard,
                interactionSource = interactionSource,
                indication = null
            )
            .padding(
                vertical = if (isSmallUI  ||  scrType == ScrType.LandscapeMedium)  8.dp  else  16.dp,
                horizontal = if (isSmallUI)  8.dp  else  16.dp
            )
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (txt.isNotEmpty()) {
            Text(
                modifier = MOD_W90
                     .padding(vertical = pTxt)
                ,
                text = txt,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
        
        otherParts()
    }
}














