package com.vankorno.vankornocompose.composables.barTop

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.vankorno.vankornohelpers.values.LibColors.GlassBlack

@Composable
fun LibTopBarShadow(                                                            modifier: Modifier
) {
    Box(
        modifier
            .height(12.dp)
            .background(
                brush = Brush.verticalGradient(listOf(Color(GlassBlack), Color.Transparent)),
                shape = RectangleShape
            )
    ) {}
}