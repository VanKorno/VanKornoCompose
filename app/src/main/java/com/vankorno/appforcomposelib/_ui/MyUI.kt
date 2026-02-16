package com.vankorno.appforcomposelib._ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.constraintlayout.compose.ConstraintLayout
import com.vankorno.appforcomposelib.H_O_M_E.LayoutsHome
import com.vankorno.appforcomposelib._menus.Popup.PopupMaker
import com.vankorno.vankornocompose.navig.PopupOFF
import com.vankorno.vankornocompose.navig.ScrHome
import com.vankorno.vankornocompose.values.LibGlobals2.libVm
import com.vankorno.vankornocompose.values.LibLayoutModifiers
import com.vankorno.vankornocompose.values.LocalScreen
import com.vankorno.vankornocompose.values.MOD_MaxSize
import com.vankorno.vankornocompose.values.MOD_MaxW
import com.vankorno.vankornocompose.vm.state

@Composable
fun MyUI() {
    ConstraintLayout(MOD_MaxSize) {
        val (body, barTop, topShadow, barBot, popup) = createRefs()
        
        val popupState by libVm.popupState.state()
        
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
        
        ScreenNavig(modifiers)
        
        
        AnimatedVisibility(popupState != PopupOFF) {
            PopupMaker(popupState, modifPopup)
        }
    }
}


@Composable
fun ScreenNavig(                                                       bodyModif: LibLayoutModifiers
) {
    when (LocalScreen.current) {
        ScrHome -> LayoutsHome(bodyModif)
        else -> {}
    }
}
