package com.vankorno.vankornocompose.theme_main

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Surface
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.vankorno.vankornocompose.values.MOD_MaxSize
import com.vankorno.vankornocompose.values.TypographyNunito

@Composable
fun LibMainScaffold(                                  statusBarColor: Color = LibColorBlackBtn,
                                                          typography: Typography = TypographyNunito,
                                                             content: @Composable ()->Unit,
) {
    LibMainTheme(
        statusBarColor = statusBarColor,
        typography = typography
    ) {
        Surface(
            modifier = MOD_MaxSize
                .windowInsetsPadding(
                    WindowInsets.systemBars.add(WindowInsets.ime)
                ),
            color = LibColorBackground
        ) {
            content()
        }
    }
}
