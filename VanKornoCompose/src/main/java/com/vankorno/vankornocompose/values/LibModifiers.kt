package com.vankorno.vankornocompose.values

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


val MOD_MaxSize = Modifier.fillMaxSize()
val MOD_MaxH = Modifier.fillMaxHeight()
val MOD_MaxW = Modifier.fillMaxWidth()
val MOD_W90 = Modifier.fillMaxWidth(0.9f)
val MOD_W50 = Modifier.fillMaxWidth(0.5f)

val MOD_DefaultSpacer = Modifier.height(35.dp)



data class LibLayoutModifiers(
                                  val body: Modifier,
                                   val top: Modifier = MOD_MaxW,
                             val topShadow: Modifier = MOD_MaxW,
                                val bottom: Modifier = MOD_MaxW,
                                 val popup: Modifier = MOD_MaxW,
                               val leftBar: Modifier = MOD_MaxH,
                              val rightBar: Modifier = MOD_MaxH,
                              val fullMenu: Modifier = MOD_MaxSize,
                                 val snack: Modifier = MOD_MaxW,
)





