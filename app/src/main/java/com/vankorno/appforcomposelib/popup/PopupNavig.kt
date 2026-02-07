package com.vankorno.appforcomposelib.popup

import androidx.compose.runtime.Composable
import com.vankorno.appforcomposelib.popup.context_info.ContextInfoPopup
import com.vankorno.vankornocompose.navig.PopupContextInfo
import com.vankorno.vankornocompose.navig.PopupState

@Composable
fun PopupNavig(                                                               popupState: PopupState
) {
    when (popupState) {
        PopupContextInfo -> { ContextInfoPopup() }
        else -> {}
    }
}











