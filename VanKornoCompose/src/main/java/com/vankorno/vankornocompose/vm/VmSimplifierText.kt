package com.vankorno.vankornocompose.vm

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class VmText(                                                  private val ssh: SavedStateHandle,
                                                               private val key: String,
                                                                       default: String = "",
                                                         private val onTextSet: (String)->Unit = {},
) {
    private val _text = MutableStateFlow(ssh.get<String>(key) ?: default)
    private val _selection = MutableStateFlow(TextRange(_text.value.length))
    
    val textFlow: StateFlow<String> = _text
    val selectionFlow: StateFlow<TextRange> = _selection
    
    
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

    fun updateFrom(value: TextFieldValue) {
        _text.value = value.text
        _selection.value = value.selection.constrain(0, value.text.length)
        ssh[key] = value.text
        onTextSet.invoke(value.text)
    }

    @Composable
    fun textState(): State<String> = _text.collectAsStateWithLifecycle()

    @Composable
    fun selectionState(): State<TextRange> = _selection.collectAsStateWithLifecycle()

    @Composable
    fun state(): State<TextFieldValue> {
        val t by textState()
        val s by selectionState()
        return androidx.compose.runtime.remember(t, s) {
            androidx.compose.runtime.mutableStateOf(TextFieldValue(t, s))
        }
    }

    private fun TextRange.constrain(min: Int, max: Int): TextRange {
        return TextRange(
            start.coerceIn(min, max),
            end.coerceIn(min, max)
        )
    }
}