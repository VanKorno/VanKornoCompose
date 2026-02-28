package com.vankorno.vankornocompose.composables.barTop

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.vankorno.vankornocompose.theme_main.LibColor
import com.vankorno.vankornohelpers.values.LibColors.WhiteTransp

@Composable
fun LibTopBarIconBtn(                                                            enabled: Boolean,
                                                                                    icon: Int,
                                                                                   click: ()->Unit,
) = LibTopBarIconBtn(enabled, icon, click) {}


@Composable
fun LibTopBarIconBtn(                                                            enabled: Boolean,
                                                                                    icon: Int,
                                                                                   click: ()->Unit,
                                                                               longClick: ()->Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    
    Box(
        Modifier
            .size(44.dp)
            .combinedClickable(
                enabled = enabled,
                onClick = click,
                onLongClick = longClick,
                interactionSource = interactionSource,
                indication = null
            )
            .padding(6.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.matchParentSize(),
            painter = painterResource(id = icon),
            tint =  if (enabled)
                        LibColor.WhiteText.color
                    else
                        Color(WhiteTransp)
            ,
            contentDescription = null
        )
    }
}
