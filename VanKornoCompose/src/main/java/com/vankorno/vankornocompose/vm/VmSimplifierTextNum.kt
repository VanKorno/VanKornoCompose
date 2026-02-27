package com.vankorno.vankornocompose.vm

import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.SavedStateHandle
import com.vankorno.vankornohelpers.toNoNullInt
import com.vankorno.vankornohelpers.toNoNullLong
import com.vankorno.vankornohelpers.toNoZeroStr


class VmNumericText(                                                       ssh: SavedStateHandle,
                                                                           key: String,
                                                                       default: String = "",
                                                                     maxLength: Int? = null,
                                                                     onTextSet: (String)->Unit = {},
) : VmText(ssh, key, default, maxLength = maxLength, maxLines = 1, onTextSet = onTextSet) {
    
    override val additionalTextModifier: (String)->String = { input ->
        input.filter { it.isDigit() }
    }
    
    fun normalizeNumber() {
        val n = text.toNoNullInt()
        text = n.toNoZeroStr()
    }
    fun asInt(): Int = text.toNoNullInt()
    fun asLong(): Long = text.toNoNullLong()
    
    
    var min: Int? = null
    var max: Int? = null
    
    fun clamp() {
        val n = asInt().coerceIn(min ?: Int.MIN_VALUE, max ?: Int.MAX_VALUE)
        text = n.toNoZeroStr()
    }
    
    
    override fun updateFrom(                                                    new: TextFieldValue
    ) {
        if (this@VmNumericText.maxLength == 1) {
            val oldText = text
            val insertedIndex = new.selection.start - 1
            val insertedChar = new.text.getOrNull(insertedIndex)
            
            if (insertedChar != null && insertedChar.isDigit()) {
                // Replace entire value with typed digit
                super.text = insertedChar.toString()
                super.selection = TextRange(1)
            } else {
                // Non-digit typed → keep old value, restore cursor
                super.text = oldText
                super.selection = TextRange(oldText.length)
            }
            return
        }
        
        // Normal behavior for maxSize > 1
        super.updateFrom(new)
    }
}



class VmDecimalText(                                                       ssh: SavedStateHandle,
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
