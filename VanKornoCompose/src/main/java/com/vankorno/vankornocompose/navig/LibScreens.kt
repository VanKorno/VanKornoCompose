package com.vankorno.vankornocompose.navig

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
open class Screen : Parcelable {
    override fun toString(): String = javaClass.simpleName
}

@Parcelize object ScrHome : Screen()
@Parcelize object ScrSett : Screen()
