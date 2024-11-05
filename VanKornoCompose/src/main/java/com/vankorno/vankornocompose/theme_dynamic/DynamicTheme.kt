package com.vankorno.vankornocompose.theme_dynamic

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import com.vankorno.vankornocompose.shared.TypographyNunito

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xff00E595),
    onPrimary = AlmostWhite,
    primaryContainer = MainIntenseColor,
    onPrimaryContainer = Color(0xffCCFFEE),
    
    secondary = Grey40, // Not used yet
    onSecondary = AlmostWhite, // Not used yet
    secondaryContainer = Grey20,
    onSecondaryContainer = AlmostWhite,
    
    error = Red80,
    onError = Red20,
    errorContainer = Red30,
    onErrorContainer = Red90,
    
    background = Color.Black,
    onBackground = AlmostWhite,
    surface = Color.Black,
    onSurface = AlmostWhite
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xff007F59),
    onPrimary = Color.White,
    primaryContainer = MainIntenseColor,
    onPrimaryContainer = Color.White,
    
    secondary = Grey60, // Not used yet
    onSecondary = Color.White, // Not used yet
    secondaryContainer = Color(0xffBAC4BC),
    onSecondaryContainer = Color(0xff0d260d),
    
    error = Red40,
    onError = Color.White,
    errorContainer = Red90,
    onErrorContainer = Red10,
    
    background = AlmostWhite,
    onBackground = AlmostBlack,
    surface = AlmostWhite,
    onSurface = AlmostBlack
)



@Composable
fun DynamicTheme(                                        darkTheme: Boolean = isSystemInDarkTheme(),
                                                      dynamicColor: Boolean = true,
                                                        topography: Typography = TypographyNunito,
                                                           content: @Composable () -> Unit
) {
    val colorScheme = when (darkTheme) {
        true -> {
            if (dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
                dynamicDarkColorScheme(LocalContext.current)
            else
                DarkColorScheme
        }
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colorScheme.primaryContainer.toArgb()
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = false
        }
    }
    
    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        content = content
    )
}