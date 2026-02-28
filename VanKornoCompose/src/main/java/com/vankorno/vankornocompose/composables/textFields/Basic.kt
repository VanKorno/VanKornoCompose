package com.vankorno.vankornocompose.composables.textFields

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.TextFieldDecorator
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import com.vankorno.vankornocompose.values.MOD_StandardTextField
import com.vankorno.vankornocompose.vm.VmText
import com.vankorno.vankornohelpers.values.LibColors.BlackT30
import kotlinx.coroutines.launch


@Composable
private fun HintText(                                                               text: String,
                                                                                fontSize: TextUnit,
) {
    Text(
        text = text,
        fontSize = fontSize,
        color = Color(BlackT30)
    )
}

@Composable
fun LibBasicTextField(
    vmText: VmText,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    lineQuantRange: IntRange = 1..Int.MAX_VALUE,
    textStyle: TextStyle = TextStyle.Default,
    hint: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActionHandler? = null,
    cursorBrush: Brush = SolidColor(Color.Black),
    enableFocusHandling: Boolean = true,
    normalizeText: (String) -> String = { it },
    interactionSource: MutableInteractionSource? = null,
    decorModif: Modifier = MOD_StandardTextField,
    wrapSelectionContainer: Boolean = true,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    decor: @Composable BoxScope.() -> Unit = {},
) {
    val requester = remember { FocusRequester() }

    LaunchedEffect(vmText) {
        launch { vmText.focusRequest.flow.collect { requester.requestFocus() } }
        launch { vmText.clearFocusRequest.flow.collect { requester.freeFocus() } }
    }

    val decorator: TextFieldDecorator = { inner ->
        Box(
            modifier = decorModif.then(if (enableFocusHandling) Modifier.focusRequester(requester) else Modifier),
            contentAlignment = Alignment.CenterStart
        ) {
            inner()
            if (vmText.text.isEmpty() && hint.isNotEmpty()) {
                HintText(hint, textStyle.fontSize)
            }
            decor()
        }
    }

    val lineLimits = if (lineQuantRange.first == 1 && lineQuantRange.last == 1) {
        TextFieldLineLimits.SingleLine
    } else {
        TextFieldLineLimits.MultiLine(lineQuantRange.first, lineQuantRange.last)
    }

    val textFieldContent: @Composable () -> Unit = {
        BasicTextField(
            state = vmText.value,
            modifier = modifier,
            enabled = enabled,
            readOnly = readOnly,
            textStyle = textStyle,
            keyboardOptions = keyboardOptions,
            onKeyboardAction = keyboardActions,
            lineLimits = lineLimits,
            onTextLayout = { getResult ->
                getResult()?.let { onTextLayout(it) }
            },
            interactionSource = interactionSource,
            cursorBrush = cursorBrush,
            decorator = decorator
        )

        if (enableFocusHandling) {
            LaunchedEffect(vmText.text) {
                val normalized = normalizeText(vmText.text)
                if (normalized != vmText.text) {
                    vmText.setText(normalized)
                }
            }
        }
    }

    if (wrapSelectionContainer) SelectionContainer { textFieldContent() }
    else textFieldContent()
}