package com.vankorno.vankornocompose.values

import androidx.compose.runtime.compositionLocalOf
import com.vankorno.vankornocompose.ScrType
import com.vankorno.vankornocompose.navig.PopState
import com.vankorno.vankornocompose.navig.PopStateOFF
import com.vankorno.vankornocompose.navig.ScrHome
import com.vankorno.vankornocompose.navig.Screen
import com.vankorno.vankornohelpers.values.LibConstants.ENG

val LocalLanguage = compositionLocalOf { ENG }

val LocalScrType = compositionLocalOf { ScrType.PortraitSmall }

val LocalScreen = compositionLocalOf<Screen> { ScrHome }

val LocalPopState = compositionLocalOf<PopState> { PopStateOFF }




