package com.vankorno.vankornocompose.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.vankorno.vankornohelpers.LibUI

private val MOD_CheckCircle = Modifier.size(37.dp).padding(6.dp)


@Composable
fun LibCheckCircleIcon(                                        isPicked: Boolean,
                                                               modifier: Modifier = MOD_CheckCircle,
                                                                  color: Color = Color.White
) {
    Icon(
            modifier = modifier,
            painter = painterResource(id = LibUI().getCircleIcon(isPicked)),
            tint = color,
            contentDescription = null
        )
}