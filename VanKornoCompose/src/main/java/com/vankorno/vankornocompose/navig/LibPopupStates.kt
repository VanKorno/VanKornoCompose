package com.vankorno.vankornocompose.navig

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize sealed interface PopupState : Parcelable

@Parcelize object PopupOFF : PopupState
@Parcelize object PopupContextInfo : PopupState


