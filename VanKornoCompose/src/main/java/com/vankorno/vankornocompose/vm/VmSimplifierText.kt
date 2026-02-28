package com.vankorno.vankornocompose.vm

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.ui.text.TextRange
import androidx.lifecycle.SavedStateHandle
import com.vankorno.vankornohelpers.normalizeNewlines
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.select

open class VmText(
    private val ssh: SavedStateHandle,
    private val key: String,
    private val default: String = "",
    val maxLength: Int? = null,
    private val maxLines: Int? = null,
    private val onTextSet: (String) -> Unit = {},
) {
    val value = TextFieldState(initialText = ssh.get<String>(key) ?: default)

    val hasFocus = VmVal(false)
    val focusRequest = VmEvent()
    val clearFocusRequest = VmEvent()

    fun requestFocus() { focusRequest.fire() }
    fun clearFocus() { clearFocusRequest.fire() }

    protected open val additionalTextModifier: (String) -> String = { it }

    protected fun textModifier(input: String): String {
        var t = input.normalizeNewlines()
        maxLines?.let { lines ->
            if (lines > 0) {
                val split = t.lines()
                if (split.size > lines) t = split.take(lines).joinToString("\n")
            }
        }
        maxLength?.let { size ->
            if (size > 0 && t.length > size) t = t.take(size)
        }
        return additionalTextModifier(t)
    }

    val text: String get() = value.text.toString()
    val selection: TextRange get() = TextRange(value.selection.start, value.selection.end)

    fun setText(newText: String) {
        val modified = textModifier(newText)
        CoroutineScope(Dispatchers.Main).launch {
            value.edit {
                replace(0, length, modified)
                select { setSelection(TextRange(modified.length)) }
            }
            ssh[key] = modified
            onTextSet(modified)
        }
    }

    fun setSelection(range: TextRange) {
        CoroutineScope(Dispatchers.Main).launch {
            value.edit {
                select { setSelection(range) }
            }
        }
    }

    fun clear() { setText("") }
    fun reset() { setText(default) }

    private fun TextRange.constrain(min: Int, max: Int) =
        TextRange(start.coerceIn(min, max), end.coerceIn(min, max))


    // --- Convenience functions ---

    fun getSelectedText(): String {
        val sel = selection
        return if (sel.start == sel.end) "" else text.substring(sel.start, sel.end)
    }

    fun insertAtCursor(insert: String) {
        val sel = selection
        val newText = text.substring(0, sel.start) + insert + text.substring(sel.end)
        setText(newText)
        setSelection(TextRange(sel.start + insert.length))
    }

    fun deleteSelection() {
        val sel = selection
        if (sel.start == sel.end) return
        setText(text.removeRange(sel.start, sel.end))
        setSelection(TextRange(sel.start))
    }

    fun moveCursorToStart() { setSelection(TextRange(0)) }
    fun moveCursorToEnd() { setSelection(TextRange(text.length)) }
    fun selectAll() { setSelection(TextRange(0, text.length)) }
    fun unselect() { setSelection(TextRange(selection.end)) }

    // Word navigation
    fun moveCursorToWordStart() {
        val idx = text.lastIndexOf(' ', selection.start - 1).let { if (it < 0) 0 else it + 1 }
        setSelection(TextRange(idx))
    }

    fun moveCursorToWordEnd() {
        val idx = text.indexOf(' ', selection.end).let { if (it < 0) text.length else it }
        setSelection(TextRange(idx))
    }

    fun selectWordAtCursor() {
        val start = text.lastIndexOf(' ', selection.start - 1).let { if (it < 0) 0 else it + 1 }
        val end = text.indexOf(' ', selection.end).let { if (it < 0) text.length else it }
        setSelection(TextRange(start, end))
    }

    // Line/paragraph navigation
    fun moveCursorToLineStart() {
        val idx = text.lastIndexOf('\n', selection.start - 1).let { if (it < 0) 0 else it + 1 }
        setSelection(TextRange(idx))
    }

    fun moveCursorToLineEnd() {
        val idx = text.indexOf('\n', selection.end).let { if (it < 0) text.length else it }
        setSelection(TextRange(idx))
    }

    fun selectLineAtCursor() {
        val start = text.lastIndexOf('\n', selection.start - 1).let { if (it < 0) 0 else it + 1 }
        val end = text.indexOf('\n', selection.end).let { if (it < 0) text.length else it }
        setSelection(TextRange(start, end))
    }
}













