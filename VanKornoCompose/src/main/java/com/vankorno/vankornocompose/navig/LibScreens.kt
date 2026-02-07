package com.vankorno.vankornocompose.navig

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize open class Screen : Parcelable

@Parcelize object ScrHome : Screen()
@Parcelize object ScrSett : Screen()
