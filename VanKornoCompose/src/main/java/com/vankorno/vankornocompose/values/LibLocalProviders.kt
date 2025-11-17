package com.vankorno.vankornocompose.values

import androidx.compose.runtime.compositionLocalOf
import com.vankorno.vankornocompose.ScrType
import com.vankorno.vankornohelpers.values.LibConstants.ENG

val LocalLanguage = compositionLocalOf { ENG }

val LocalScrType = compositionLocalOf { ScrType.PortraitSmall }