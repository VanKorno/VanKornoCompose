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
import com.vankorno.vankornocompose.LibScreen.Companion.scrType
import com.vankorno.vankornocompose.values.LibGlobals2
import com.vankorno.vankornocompose.values.LibGlobals2.libVm
import com.vankorno.vankornocompose.values.LocalLanguage
import com.vankorno.vankornocompose.values.LocalPopupState
import com.vankorno.vankornocompose.values.LocalScrType
import com.vankorno.vankornocompose.values.LocalScreen
import com.vankorno.vankornocompose.values.MOD_MaxSize
import com.vankorno.vankornocompose.vm.state
import com.vankorno.vankornohelpers.values.clearFocus
import com.vankorno.vankornohelpers.values.hideKeyboard
import com.vankorno.vankornohelpers.values.showKeyboard


@Composable
fun LibMainScaffold(                              statusBarColor: Color = LibColor.BlackSurf.color,
                                                      typography: Typography = Typography(),
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
            color = LibColor.Background.color
        ) {
            KeyboardActions()
            
            val lang by LibGlobals2.language.state()
            val scrType by scrType.state()
            val currScreen by libVm.currScreen.state()
            val popState by libVm.popupState.state()
            
            CompositionLocalProvider(
                LocalLanguage provides lang,
                LocalScrType provides scrType,
                LocalScreen provides currScreen,
                LocalPopupState provides popState,
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