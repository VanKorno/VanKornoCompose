package com.vankorno.vankornocompose.vm

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

open class VmText(                                             private val ssh: SavedStateHandle,
                                                               private val key: String,
                                                                       default: String = "",
                                                                       maxSize: Int? = null,
                                                                      maxLines: Int? = null,
                                                         private val onTextSet: (String)->Unit = {},
) {
    private val _text = MutableStateFlow(ssh.get<String>(key) ?: default)
    val textFlow: StateFlow<String> get() = _text
    
    private val _selection = MutableStateFlow(TextRange(_text.value.length))
    val selectionFlow: StateFlow<TextRange> get() = _selection
    
    
    var value: TextFieldValue
        get() = TextFieldValue(text, selection)
        set(new) = updateFrom(new)
    
    var text: String
        get() = _text.value
        set(new) {
            _text.value = new
            ssh[key] = new
            _selection.value = _selection.value.constrain(0, new.length)
            onTextSet.invoke(new)
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
    
    // Insert text at cursor / replace selection
    fun insertAtCursor(insert: String) {
        val sel = selection
        val newText = text.substring(0, sel.start) + insert + text.substring(sel.end)
        text = newText
        val newCursor = sel.start + insert.length
        selection = TextRange(newCursor)
    }
    
    // Put cursor at start or end
    fun moveCursorToStart() { selection = TextRange(0) }
    fun moveCursorToEnd() { selection = TextRange(text.length) }
    
    // Select all / unselect
    fun selectAll() { selection = TextRange(0, text.length) }
    fun unselect() { selection = TextRange(selection.end) }
    
    
    fun deleteSelection() {
        val sel = selection
        if (sel.start == sel.end) return
        text = text.removeRange(sel.start, sel.end)
        selection = TextRange(sel.start)
    }
    
    
    // Words
    
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
    
    // Lines, paragraphs
    
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
    
    
    fun getSelectedText(): String {
        val sel = selection
        return if (sel.start == sel.end) ""
               else text.substring(sel.start, sel.end)
    }
}