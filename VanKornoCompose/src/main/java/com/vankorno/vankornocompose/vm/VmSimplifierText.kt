package com.vankorno.vankornocompose.vm

import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.flow.MutableStateFlow

class VmTextVal(
    private val ssh: SavedStateHandle,
    private val key: String,
    default: TextFieldValue = TextFieldValue(),
    private val onSet: ((TextFieldValue) -> Unit)? = null,
) : VmValueHolder<TextFieldValue> {

    override val default = default

    override val flow = MutableStateFlow(
        TextFieldValue(ssh.get<String>(key) ?: default.text)
    )

    override var value: TextFieldValue
        get() = flow.value
        set(new) {
            flow.value = new
            ssh[key] = new.text
            onSet?.invoke(new)
        }

    var text: String
        get() = value.text
        set(new) {
            val sel = value.selection.constrain(0, new.length)
            value = value.copy(text = new, selection = sel)
        }

    var selection: TextRange
        get() = value.selection
        set(range) {
            val safe = range.constrain(0, value.text.length)
            value = value.copy(selection = safe)
        }

    private fun TextRange.constrain(min: Int, max: Int): TextRange {
        return TextRange(
            start.coerceIn(min, max),
            end.coerceIn(min, max)
        )
    }
}