package com.vankorno.vankornocompose.composables

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.vankorno.vankornocompose.values.MOD_CheckCircle
import com.vankorno.vankornohelpers.LibUI

@Composable
fun LibCheckCircleIcon(                                                         isPicked: Boolean
) {
    Icon(
            modifier = MOD_CheckCircle,
            painter = painterResource(id = LibUI().getCircleIcon(isPicked)),
            tint = Color.White,
            contentDescription = null
        )
}