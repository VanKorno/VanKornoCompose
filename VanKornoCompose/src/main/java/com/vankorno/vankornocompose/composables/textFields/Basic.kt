package com.vankorno.vankornocompose.composables.textFields

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import com.vankorno.vankornocompose.actions.applyIf
import com.vankorno.vankornocompose.vm.VmEvent
import com.vankorno.vankornocompose.vm.collect

@Composable
fun LibBasicTextField(
                         value: TextFieldValue,
                 onValueChange: (TextFieldValue)->Unit,
                      modifier: Modifier = Modifier,
                       enabled: Boolean = true,
                      readOnly: Boolean = false,
                     textStyle: TextStyle = TextStyle.Default,
               keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
               keyboardActions: KeyboardActions = KeyboardActions.Default,
                    singleLine: Boolean = false,
          visualTransformation: VisualTransformation = VisualTransformation.None,
                  onTextLayout: (TextLayoutResult)->Unit = {},
             interactionSource: MutableInteractionSource? = null,
                   cursorBrush: Brush = SolidColor(Color.Black),
           enableFocusHandling: Boolean = true,
             focusChangeLambda: ((Boolean)->Unit)? = null,
                  focusRequest: VmEvent? = null,
             clearFocusRequest: VmEvent? = null,
        wrapSelectionContainer: Boolean = true,
                      maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
                      minLines: Int = 1,
                 decorationBox: @Composable (innerTextField: @Composable ()->Unit)->Unit = { it() },
) {
    val requester = remember { FocusRequester() }

    focusRequest?.collect { requester.requestFocus() }
    clearFocusRequest?.collect { requester.freeFocus() }

    val combinedModifier = modifier.applyIf(enableFocusHandling) {
        focusRequester(requester)
            .onFocusChanged { focusChangeLambda?.invoke(it.isFocused) }
    }

    val textFieldContent: @Composable () -> Unit = {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = combinedModifier,
            enabled = enabled,
            readOnly = readOnly,
            textStyle = textStyle,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = singleLine,
            maxLines = maxLines,
            minLines = minLines,
            visualTransformation = visualTransformation,
            onTextLayout = onTextLayout,
            interactionSource = interactionSource ?: remember { MutableInteractionSource() },
            cursorBrush = cursorBrush,
            decorationBox = decorationBox
        )
    }

    if (wrapSelectionContainer) SelectionContainer { textFieldContent() }
    else textFieldContent()
}