package com.vankorno.vankornocompose.vm

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vankorno.vankornohelpers.normalizeNewlines
import com.vankorno.vankornohelpers.toNoNullInt
import com.vankorno.vankornohelpers.toNoNullLong
import com.vankorno.vankornohelpers.toNoZeroStr
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

open class VmText(                                             private val ssh: SavedStateHandle,
                                                               private val key: String,
                                                           private val default: String = "",
                                                           val maxSize: Int? = null,
                                                          private val maxLines: Int? = null,
                                                         private val onTextSet: (String)->Unit = {},
) {
    private val _text = MutableStateFlow(ssh.get<String>(key) ?: default)
    val textFlow: StateFlow<String> get() = _text
    
    private val _selection = MutableStateFlow(TextRange(_text.value.length))
    val selectionFlow: StateFlow<TextRange> get() = _selection
    
    protected open val additionalTextModifier: (String) -> String = { it }
    
    protected fun textModifier(input: String): String {
        var t = input.normalizeNewlines()
        maxLines?.let { lines ->
            if (lines > 0) {
                val split = t.lines()
                if (split.size > lines) t = split.take(lines).joinToString("\n")
            }
        }
        maxSize?.let { size ->
            if (size > 0 && t.length > size) t = t.take(size)
        }
        return additionalTextModifier(t)
    }
    
    var value: TextFieldValue
        get() = TextFieldValue(text, selection)
        set(new) = updateFrom(new)
    
    var text: String
        get() = _text.value
        set(new) {
            val modified = textModifier(new)
            _text.value = modified
            ssh[key] = modified
            _selection.value = _selection.value.constrain(0, modified.length)
            onTextSet.invoke(modified)
        }
    
    var selection: TextRange
        get() = _selection.value
        set(range) {
            _selection.value = range.constrain(0, _text.value.length)
        }
    
    fun clear() {
        text = ""
        selection = TextRange(0)
    }
    
    fun reset() {
        if (text.isEmpty()) text = default
    }
    
    fun updateFrom(new: TextFieldValue) {
        text = new.text
        selection = new.selection
    }
    
    @Composable
    fun textState(): State<String> = _text.collectAsStateWithLifecycle()
    
    @Composable
    fun selectionState(): State<TextRange> = _selection.collectAsStateWithLifecycle()
    
    @Composable
    fun state(): State<TextFieldValue> {
        val t by textState()
        val s by selectionState()
        return remember(t, s) { androidx.compose.runtime.mutableStateOf(TextFieldValue(t, s)) }
    }
    
    private fun TextRange.constrain(min: Int, max: Int): TextRange {
        return TextRange(start.coerceIn(min, max), end.coerceIn(min, max))
    }
    
    
    //    C O N V E N I E N C E
    fun getSelectedText(): String {
        val sel = selection
        return if (sel.start == sel.end)
                    ""
               else
                   text.substring(sel.start, sel.end)
    }
    
    // Insert text at cursor / replace selection
    fun insertAtCursor(insert: String) {
        val sel = selection
        val newText = text.substring(0, sel.start) + insert + text.substring(sel.end)
        text = newText
        selection = TextRange(sel.start + insert.length)
    }
    
    fun moveCursorToStart() { selection = TextRange(0) }
    fun moveCursorToEnd() { selection = TextRange(text.length) }
    
    fun selectAll() { selection = TextRange(0, text.length) }
    fun unselect() { selection = TextRange(selection.end) }
    
    fun deleteSelection() {
        val sel = selection
        if (sel.start == sel.end) return
        text = text.removeRange(sel.start, sel.end)
        selection = TextRange(sel.start)
    }
    
    
    // Word navigation
    
    fun moveCursorToWordStart() {
        val idx = text.lastIndexOf(' ', selection.start - 1).let { if (it < 0) 0 else it + 1 }
        selection = TextRange(idx)
    }
    
    fun moveCursorToWordEnd() {
        val idx = text.indexOf(' ', selection.end).let { if (it < 0) text.length else it }
        selection = TextRange(idx)
    }
    
    fun selectWordAtCursor() {
        val start = text.lastIndexOf(' ', selection.start - 1).let { if (it < 0) 0 else it + 1 }
        val end = text.indexOf(' ', selection.end).let { if (it < 0) text.length else it }
        selection = TextRange(start, end)
    }
    
    
    // Line/paragraph navigation
    
    fun moveCursorToLineStart() {
        val idx = text.lastIndexOf('\n', selection.start - 1).let { if (it < 0) 0 else it + 1 }
        selection = TextRange(idx)
    }
    
    fun moveCursorToLineEnd() {
        val idx = text.indexOf('\n', selection.end).let { if (it < 0) text.length else it }
        selection = TextRange(idx)
    }
    
    fun selectLineAtCursor() {
        val start = text.lastIndexOf('\n', selection.start - 1).let { if (it < 0) 0 else it + 1 }
        val end = text.indexOf('\n', selection.end).let { if (it < 0) text.length else it }
        selection = TextRange(start, end)
    }
}



class VmNumericText(                                                       ssh: SavedStateHandle,
                                                                           key: String,
                                                                       default: String = "",
                                                                       maxSize: Int? = null,
                                                                     onTextSet: (String)->Unit = {},
) : VmText(ssh, key, default, maxSize = maxSize, maxLines = 1, onTextSet = onTextSet) {
    
    override val additionalTextModifier: (String) -> String = { input ->
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
}



class VmDecimalText(                                                       ssh: SavedStateHandle,
                                                                           key: String,
                                                                       default: String = "",
                                                                       maxSize: Int? = null,
                                                                     onTextSet: (String)->Unit = {},
) : VmText(ssh, key, default, maxSize = maxSize, maxLines = 1, onTextSet = onTextSet) {
    
    override val additionalTextModifier: (String) -> String = { input ->
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
        val n = asDouble().coerceIn(min ?: Double.MIN_VALUE, max ?: Double.MAX_VALUE)
        text = if (n == 0.0) "" else n.toString()
    }
}



class VmPasswordText(                                                      ssh: SavedStateHandle,
                                                                           key: String,
                                                                       default: String = "",
                                                                       maxSize: Int? = null,
                                                                   val minSize: Int? = null,
                                                                     onTextSet: (String)->Unit = {},
) : VmText(ssh, key, default, maxSize = maxSize, maxLines = 1, onTextSet = onTextSet) {
    
    fun isValid(): Boolean {
        val len = text.length
        minSize?.let { if (len < it) return false }
        maxSize?.let { if (len > it) return false }
        return true
    }
}



class VmEmailText(                                                         ssh: SavedStateHandle,
                                                                           key: String,
                                                                       default: String = "",
                                                                       maxSize: Int? = null,
                                                                     onTextSet: (String)->Unit = {},
) : VmText(ssh, key, default, maxSize = maxSize, maxLines = 1, onTextSet = onTextSet) {
    
    fun isValid(): Boolean {
        val trimmed = text.trim()
        return trimmed.contains('@') && trimmed.substringAfter('@').contains('.')
    }
}



class VmUrlText(                                                           ssh: SavedStateHandle,
                                                                           key: String,
                                                                       default: String = "",
                                                                       maxSize: Int? = null,
                                                                     onTextSet: (String)->Unit = {},
) : VmText(ssh, key, default, maxSize = maxSize, maxLines = 1, onTextSet = onTextSet) {
    
    fun normalizeUrl(                                           defaultScheme: String = "https: //"
    ) {
        if (text.isEmpty()) return
        if (!text.startsWith("http://") && !text.startsWith("https://")) {
            text = defaultScheme + text
        }
    }
    
    fun isValid(): Boolean = text.startsWith("http://") || text.startsWith("https://")
}



class VmHexText(                                                           ssh: SavedStateHandle,
                                                                           key: String,
                                                                       default: String = "",
                                                                       maxSize: Int? = null,
                                                                     onTextSet: (String)->Unit = {},
) : VmText(ssh, key, default, maxSize = maxSize, maxLines = 1, onTextSet = onTextSet) {
    
    override val additionalTextModifier: (String) -> String = { input ->
        input.filter { it.isDigit() || it in 'A'..'F' || it in 'a'..'f' }
    }
}




