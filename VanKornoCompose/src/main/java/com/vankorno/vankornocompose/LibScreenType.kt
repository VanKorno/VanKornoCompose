package com.vankorno.vankornocompose

import android.app.Activity
import android.content.res.Configuration
import android.util.Log
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vankorno.vankornocompose.LibScreen.Companion.scrDiff1
import com.vankorno.vankornocompose.LibScreen.Companion.scrDiff2
import com.vankorno.vankornocompose.LibScreen.Companion.scrDiff3
import com.vankorno.vankornocompose.LibScreen.Companion.scrDiff4
import com.vankorno.vankornocompose.LibScreen.Companion.scrDiff5
import com.vankorno.vankornocompose.view_model.LibVm
import com.vankorno.vankornohelpers.LibUI
import com.vankorno.vankornohelpers.values.LibGlobals

const val ScrMICRO = 0
const val ScrSMALL = 1
const val ScrMEDIUM = 2
const val ScrLARGE = 3

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


class LibScreen {
    companion object {
        var microUI = false
        var smallUI = false
        var largeUI = false
        
        var scrDiff1 = 0
        var scrDiff2 = 0
        var scrDiff3 = 0
        var scrDiff4 = 0
        var scrDiff5 = 0
    }
    
    fun scrConfig(                                                            act: Activity,
                                                                              lib: LibVm,
                                                                            color: Int = -0xe4e4e5
    ) {
        LibUI().setWindowBackgroundColor(act, color)
        val screenType = calculateScreenType(act.resources.configuration)
        lib.updateScreenType(screenType)
        LibGlobals.screenDensity = act.resources.displayMetrics.density
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
        
        largeUI = scrType == ScrType.LandscapeLarge || scrType == ScrType.PortraitLarge
        
        setScrDiff()
        
        return scrType
    }
    
    
    private fun setScrDiff() {
        if (microUI)
            return //\/\/\/\/\/\
        else if (smallUI)
            setScrDiffByAddend(1)
        else if (largeUI)
            setScrDiffByAddend(3)
        else // Normal scr
            setScrDiffByAddend(2)
    }
    private fun setScrDiffByAddend(                                               addend: Int
    ) {
        scrDiff1 = addend
        scrDiff2 = addend * 2
        scrDiff3 = addend * 3
        scrDiff4 = addend * 4
        scrDiff5 = addend * 5
    }
}

fun Int.dp1() = (this + scrDiff1).dp
fun Int.dp2() = (this + scrDiff2).dp
fun Int.dp3() = (this + scrDiff3).dp
fun Int.dp4() = (this + scrDiff4).dp
fun Int.dp5() = (this + scrDiff5).dp

fun Int.sp1() = (this + scrDiff1).sp
fun Int.sp2() = (this + scrDiff2).sp
fun Int.sp3() = (this + scrDiff3).sp
fun Int.sp4() = (this + scrDiff4).sp
fun Int.sp5() = (this + scrDiff5).sp



