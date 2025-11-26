package com.vankorno.vankornocompose.composables.barBot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.vankorno.vankornocompose.dp1


@Composable
fun LibBotBar(                                              modifier: Modifier,
                                                             content: @Composable RowScope.()->Unit,
) {
    val gradientBot = listOf(Color.Transparent, Color.Black)
    
    Row(
        modifier
            .heightIn(min = 30.dp)
            .background(brush = Brush.verticalGradient(gradientBot))
            .padding(2.dp1())
        ,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        content()
    }
}