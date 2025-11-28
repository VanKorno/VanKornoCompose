package com.vankorno.vankornocompose.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.vankorno.vankornocompose.theme_main.LibColor
import com.vankorno.vankornocompose.values.LibIcon
import com.vankorno.vankornohelpers.LibUI

private val MOD_CheckCircle = Modifier.size(37.dp).padding(6.dp)


@Composable
fun LibCheckCircleIcon(                                    picked: Boolean,
                                                         modifier: Modifier = MOD_CheckCircle,
                                                            color: Color = LibColor.WhiteText.color,
) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = LibUI().getCircleIcon(picked)),
        tint = color,
        contentDescription = null
    )
}



@Composable
fun LibPinIcon(                                            pinned: Boolean,
                                                         modifier: Modifier = MOD_CheckCircle,
                                                      colorPinned: Color = Color.Green,
                                                   colorNotPinned: Color = LibColor.WhiteText.color,
) {
    Icon(
        modifier = modifier,
        painter = painterResource(
            id = if (pinned)
                LibIcon.PinFilled
            else
                LibIcon.Pin
        ),
        tint = if (pinned) colorPinned else colorNotPinned,
        contentDescription = null
    )
}









