package com.vankorno.appforcomposelib.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vankorno.appforcomposelib.popup.PopupMaker
import com.vankorno.appforcomposelib.scr__Home.LayoutsHome
import com.vankorno.vankornocompose.LibMainActivity.Companion.libVm
import com.vankorno.vankornocompose.navig.PopStateOFF
import com.vankorno.vankornocompose.navig.ScrHome
import com.vankorno.vankornocompose.values.LibLayoutModifiers
import com.vankorno.vankornocompose.values.LocalScreen
import com.vankorno.vankornocompose.values.MOD_MaxSize
import com.vankorno.vankornocompose.values.MOD_MaxW

@Composable
fun MyUI() {
    ConstraintLayout(MOD_MaxSize) {
        val (body, barTop, topShadow, barBot, popup) = createRefs()
        
        val popState by libVm.popStateFlow.collectAsStateWithLifecycle()
        
        val modifBody = MOD_MaxSize.constrainAs(body) {
            top.linkTo(barTop.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
        }
        val modifTop = MOD_MaxW.constrainAs(barTop) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        val modifTopShadow = MOD_MaxW
            .constrainAs(topShadow) {
                top.linkTo(barTop.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        val modifBot = MOD_MaxW
            .constrainAs(barBot) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        val modifPopup = MOD_MaxSize
            .constrainAs(popup) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        
        val modifiers = LibLayoutModifiers(modifBody, modifTop, modifTopShadow, modifBot, modifPopup)
        
        ScrNavig(modifiers)
        
        
        AnimatedVisibility(popState != PopStateOFF) {
            PopupMaker(popState, modifPopup)
        }
    }
}


@Composable
fun ScrNavig(                                                          bodyModif: LibLayoutModifiers
) {
    when (LocalScreen.current) {
        ScrHome -> LayoutsHome(bodyModif)
        else -> {}
    }
}
