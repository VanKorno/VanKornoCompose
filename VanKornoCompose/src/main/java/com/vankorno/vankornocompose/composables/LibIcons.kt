package com.vankorno.vankornocompose.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.vankorno.vankornocompose.R
import com.vankorno.vankornohelpers.LibUI

private val MOD_CheckCircle = Modifier.size(37.dp).padding(6.dp)


@Composable
fun LibCheckCircleIcon(                                          picked: Boolean,
                                                               modifier: Modifier = MOD_CheckCircle,
                                                                  color: Color = Color.White
) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = LibUI().getCircleIcon(picked)),
        tint = color,
        contentDescription = null
    )
}



@Composable
fun LibPinIcon(                                                  pinned: Boolean,
                                                               modifier: Modifier = MOD_CheckCircle,
                                                            colorPinned: Color = Color.Green,
                                                         colorNotPinned: Color = Color.White
) {
    Icon(
        modifier = modifier,
        painter = painterResource(
            id = if (pinned)
                R.drawable.ic_pin_filled
            else
                R.drawable.ic_pin
        ),
        tint = if (pinned) colorPinned else colorNotPinned,
        contentDescription = null
    )
}









