package com.vankorno.vankornocompose

import android.content.res.Configuration
import android.util.Log
import com.vankorno.vankornocompose.ScreenType.Companion.ScrLARGE
import com.vankorno.vankornocompose.ScreenType.Companion.ScrMEDIUM
import com.vankorno.vankornocompose.ScreenType.Companion.ScrMICRO
import com.vankorno.vankornocompose.ScreenType.Companion.ScrSMALL

class ScreenType {
    companion object {
        const val ScrMICRO = 0
        const val ScrSMALL = 1
        const val ScrMEDIUM = 2
        const val ScrLARGE = 3
        
        var microUI = false
        var smallUI = false
    }
    
    fun calculateScreenType(                                          configuration: Configuration  // (resources.configuration)
    ): ScrType {
        val width = configuration.screenWidthDp
        val height = configuration.screenHeightDp
        val isVertical = height >= width
        
        val scrType = if (isVertical) {
            when {
                height < 450 -> ScrType.PortraitMicro
                height < 600 -> ScrType.PortraitSmall
                height < 900 -> ScrType.PortraitMedium
                else -> ScrType.PortraitLarge
            }
        } else {
            when {
                width < 450 -> ScrType.LandscapeMicro
                width < 600 -> ScrType.LandscapeSmall
                width < 900 -> ScrType.LandscapeMedium
                else -> ScrType.LandscapeLarge
            }
        }
        // region LOG
        Log.d("ScreenType", "width = $width, height = $height")
        // endregion
        
        microUI = scrType == ScrType.LandscapeMicro  ||  scrType == ScrType.PortraitMicro
        
        smallUI =   microUI 
                    || scrType == ScrType.LandscapeSmall
                    || scrType == ScrType.PortraitSmall
        return scrType
    }
}

enum class ScrType(                                                             val size: Int,
                                                                          val isPortrait: Boolean
) {
    PortraitMicro(ScrMICRO, true),
    PortraitSmall(ScrSMALL, true),
    PortraitMedium(ScrMEDIUM, true),
    PortraitLarge(ScrLARGE, true),
    
    LandscapeMicro(ScrMICRO, false),
    LandscapeSmall(ScrSMALL, false),
    LandscapeMedium(ScrMEDIUM, false),
    LandscapeLarge(ScrLARGE, false)
}