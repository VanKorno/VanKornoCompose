package com.vankorno.vankornocompose.theme_main

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Surface
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vankorno.vankornocompose.LibMainActivity.Companion.libVm
import com.vankorno.vankornocompose.LibScreen.Companion.scrTypeFlow
import com.vankorno.vankornocompose.values.LibGlobals2.currScrFlow
import com.vankorno.vankornocompose.values.LibGlobals2.langFlow
import com.vankorno.vankornocompose.values.LocalLanguage
import com.vankorno.vankornocompose.values.LocalPopState
import com.vankorno.vankornocompose.values.LocalScrType
import com.vankorno.vankornocompose.values.LocalScreen
import com.vankorno.vankornocompose.values.MOD_MaxSize
import com.vankorno.vankornocompose.values.TypographyNunito
import com.vankorno.vankornohelpers.values.clearFocus
import com.vankorno.vankornohelpers.values.hideKeyboard
import com.vankorno.vankornohelpers.values.showKeyboard


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
            KeyboardActions()
            
            val lang by langFlow.collectAsStateWithLifecycle()
            val scrType by scrTypeFlow.collectAsStateWithLifecycle()
            val currScreen by currScrFlow.collectAsStateWithLifecycle()
            val popState by libVm.popStateFlow.collectAsStateWithLifecycle()
            
            CompositionLocalProvider(
                LocalLanguage provides lang,
                LocalScrType provides scrType,
                LocalScreen provides currScreen,
                LocalPopState provides popState,
            ) {
                content()
            }
        }
    }
}


@Composable
private fun KeyboardActions() {
    val keyboard = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    
    SideEffect {
        showKeyboard = { keyboard?.show() }
        hideKeyboard = { keyboard?.hide() }
        
        clearFocus = {
            try {
                focusManager.clearFocus()
            } catch (_: Exception) {}
        }
    }
}