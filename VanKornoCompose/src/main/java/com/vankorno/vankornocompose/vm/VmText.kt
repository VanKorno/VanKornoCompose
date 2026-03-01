package com.vankorno.vankornocompose.vm

import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.placeCursorAtEnd
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.text.TextRange
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vankorno.vankornohelpers.normalizeNewlines
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

open class VmText(                                             private val ssh: SavedStateHandle,
                                                               private val key: String,
                                                           private val default: String = "",
                                                                 val maxLength: Int? = null,
                                                          private val maxLines: Int? = null,
                                                         private val onTextSet: (String)->Unit = {},
) {
    val value = TextFieldState(initialText = ssh.get<String>(key) ?: default)
    
    val hasFocus = VmVal(false)
    val focusRequest = VmEvent()
    val clearFocusRequest = VmEvent()
    
    fun requestFocus() { focusRequest.fire() }
    fun clearFocus() { clearFocusRequest.fire() }
    
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private val mutex = Mutex()
    
    var text: String
        get() = value.text.toString()
        set(new) {
            scope.launch {
                mutex.withLock {
                    withContext(Dispatchers.Main) {
                        val modified = new.normalizeNewlines()
                        value.edit {
                            replace(0, length, modified)
                            selection = TextRange(
                                selection.start.coerceIn(0, modified.length),
                                selection.end.coerceIn(0, modified.length)
                            )
                        }
                        ssh[key] = modified
                        onTextSet(modified)
                    }
                }
            }
        }
    
    
    var selection: TextRange
        get() = TextRange(value.selection.start, value.selection.end)
        set(range) {
            scope.launch {
                mutex.withLock {
                    withContext(Dispatchers.Main) {
                        val len = value.text.length
                        value.edit {
                            selection = TextRange(
                                range.start.coerceIn(0, len),
                                range.end.coerceIn(0, len)
                            )
                        }
                    }
                }
            }
        }
    
    val inputTransform: InputTransformation
        get() = InputTransformation {
            // Replaceable single char
            maxLength?.let { size ->
                if (size == 1 && length > 0) {
                    replace(0, length, asCharSequence().last().toString())
                    placeCursorAtEnd()
                }
            }
            
            // Apply maxLines
            maxLines?.let { lines ->
                if (lines > 0) {
                    val split = asCharSequence().lines()
                    if (split.size > lines) {
                        replace(0, length, split.take(lines).joinToString("\n"))
                        placeCursorAtEnd()
                    }
                }
            }
    
            // Apply maxLength
            maxLength?.let { size ->
                if (size in 1..<length) {
                    replace(0, length, asCharSequence().take(size))
                    placeCursorAtEnd()
                }
            }
    
            // Apply additionalTextModifier (digits-only, etc.)
            val transformed = asCharSequence().toString().let(additionalTextModifier)
            if (transformed != asCharSequence().toString()) {
                replace(0, length, transformed)
                placeCursorAtEnd()
            }
        }
    
    val outputTransform: OutputTransformation
        get() = OutputTransformation {  }
    
    protected open val additionalTextModifier: (String) -> String = { it }
    
    
    fun clear() {
        text = ""
    }
    
    fun reset() {
        text = default
    }
    
    
    fun getSelectedText(): String {
        val sel = selection
        return if (sel.start == sel.end) "" else text.substring(sel.start, sel.end)
    }
    
    fun insertAtCursor(insert: String) {
        val sel = selection
        text = text.substring(0, sel.start) + insert + text.substring(sel.end)
        selection = TextRange(sel.start + insert.length)
    }
    
    fun deleteSelection() {
        val sel = selection
        if (sel.start == sel.end) return
        text = text.removeRange(sel.start, sel.end)
        selection = TextRange(sel.start)
    }
    
    fun moveCursorToStart() { selection = TextRange(0) }
    fun moveCursorToEnd() { selection = TextRange(text.length) }
    fun selectAll() { selection = TextRange(0, text.length) }
    fun unselect() { selection = TextRange(selection.end) }
    
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
    
    
    @Composable
    fun textState(): State<String> = snapshotFlow { value.text.toString() }
        .collectAsStateWithLifecycle(initialValue = value.text.toString())
    
    @Composable
    fun selectionState(): State<TextRange> = snapshotFlow { value.selection }
        .collectAsStateWithLifecycle(initialValue = value.selection)
    
}
