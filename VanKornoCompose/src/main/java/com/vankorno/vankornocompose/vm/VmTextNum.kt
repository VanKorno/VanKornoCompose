package com.vankorno.vankornocompose.vm

import androidx.lifecycle.SavedStateHandle
import com.vankorno.vankornohelpers.toNoNullInt
import com.vankorno.vankornohelpers.toNoNullLong
import com.vankorno.vankornohelpers.toNoZeroStr


class VmTextNum(                                                           ssh: SavedStateHandle,
                                                                           key: String,
                                                                       default: String = "",
                                                                     maxLength: Int? = null,
                                                                     onTextSet: (String)->Unit = {},
) : VmText(ssh, key, default, maxLength = maxLength, maxLines = 1, onTextSet = onTextSet) {
    
    override val additionalTextModifier: (String)->String = { input ->
        input.filter { it.isDigit() }
    }
    
    fun asInt(): Int = text.toNoNullInt()
    fun asLong(): Long = text.toNoNullLong()
    
    
    var min: Int? = null
    var max: Int? = null
    
    fun clamp() {
        val n = asInt().coerceIn(min ?: Int.MIN_VALUE, max ?: Int.MAX_VALUE)
        text = n.toNoZeroStr()
    }
}



class VmTextDecimal(                                                       ssh: SavedStateHandle,
                                                                           key: String,
                                                                       default: String = "",
                                                                     maxLength: Int? = null,
                                                                     onTextSet: (String)->Unit = {},
) : VmText(ssh, key, default, maxLength = maxLength, maxLines = 1, onTextSet = onTextSet) {
    
    override val additionalTextModifier: (String)->String = { input ->
        val filtered = input.filter { it.isDigit() || it == '.' }
        val firstDotIndex = filtered.indexOf('.')
        if (firstDotIndex < 0) filtered
        else filtered.take(firstDotIndex + 1) + filtered.drop(firstDotIndex + 1).replace(".", "")
    }
    
    fun normalizeNumber() {
        val n = text.toDoubleOrNull() ?: 0.0
        text = if (n == 0.0) "" else n.toString()
    }
    
    fun asDouble(): Double = text.toDoubleOrNull() ?: 0.0
    fun asFloat(): Float = text.toFloatOrNull() ?: 0f
    
    var min: Double? = null
    var max: Double? = null
    
    fun clamp() {
        val n = asDouble().coerceIn(min ?: -Double.MAX_VALUE, max ?: Double.MAX_VALUE)
        text = if (n == 0.0) "" else n.toString()
    }
}



internal fun normalizeNumTextField(                                          text: String,
                                                                   canHaveOneZero: Boolean = false,
) = if (canHaveOneZero)
        text.toNoNullInt().toString()
    else
        text.toNoNullInt().toNoZeroStr()





