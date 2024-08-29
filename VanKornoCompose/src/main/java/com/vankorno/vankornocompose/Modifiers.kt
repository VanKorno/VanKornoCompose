package com.vankorno.vankornocompose

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier

class Modifiers {
    companion object {
        val MOD_MaxSize = Modifier.fillMaxSize()
        val MOD_MaxH = Modifier.fillMaxHeight()
        val MOD_MaxW = Modifier.fillMaxWidth()
        val MOD_W50 = Modifier.fillMaxWidth(0.5f)
    }
}