package com.vankorno.vankornocompose.values

import androidx.compose.runtime.compositionLocalOf
import com.vankorno.vankornocompose.ScrType
import com.vankorno.vankornocompose.navig.PopupState
import com.vankorno.vankornocompose.navig.PopupOFF
import com.vankorno.vankornocompose.navig.ScrHome
import com.vankorno.vankornocompose.navig.Screen
import com.vankorno.vankornohelpers.values.LibLangConst.ENG

val LocalLanguage = compositionLocalOf { ENG }

val LocalScrType = compositionLocalOf { ScrType.PortraitSmall }

val LocalScreen = compositionLocalOf<Screen> { ScrHome }

val LocalPopupState = compositionLocalOf<PopupState> { PopupOFF }




