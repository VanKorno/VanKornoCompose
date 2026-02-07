package com.vankorno.vankornocompose.navig

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed interface Screen : Parcelable {
    val name: String
        get() = this::class.simpleName!!
}

@Parcelize object ScrHome : Screen
@Parcelize object ScrSett : Screen
