package com.vankorno.vankornocompose.navig

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize open class PopupState : Parcelable {
    override fun toString(): String = javaClass.simpleName
}

@Parcelize object PopupOFF : PopupState()
@Parcelize object PopupContextInfo : PopupState()


