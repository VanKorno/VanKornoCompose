package com.vankorno.vankornocompose.composables.textFields

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import com.vankorno.vankornocompose.vm.VmEvent
import com.vankorno.vankornocompose.vm.normalizeNumTextField

@Composable
fun LibBasicNumTextField(
                           value: TextFieldValue,
                   onValueChange: (TextFieldValue)->Unit,
                        modifier: Modifier = Modifier,
                         enabled: Boolean = true,
                        readOnly: Boolean = false,
                       textStyle: TextStyle = TextStyle.Default,
                 keyboardActions: KeyboardActions = KeyboardActions.Default,
             enableFocusHandling: Boolean = true,
                    focusRequest: VmEvent? = null,
               clearFocusRequest: VmEvent? = null,
          wrapSelectionContainer: Boolean = true,
            visualTransformation: VisualTransformation = VisualTransformation.None,
                    onTextLayout: (TextLayoutResult)->Unit = {},
               interactionSource: MutableInteractionSource? = null,
                     cursorBrush: Brush = SolidColor(Color.Black),
                  canHaveOneZero: Boolean = false,
                   decorationBox: @Composable (innerTextField: @Composable ()->Unit)->Unit = { it },
) {
    LibBasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        keyboardActions = keyboardActions,
        singleLine = true,
        enableFocusHandling = enableFocusHandling,
        focusRequest = focusRequest,
        clearFocusRequest = clearFocusRequest,
        wrapSelectionContainer = wrapSelectionContainer,
        visualTransformation = visualTransformation,
        onTextLayout = onTextLayout,
        interactionSource = interactionSource,
        cursorBrush = cursorBrush,
        decorationBox = decorationBox,
        focusChangeLambda = { focused ->
            if (!focused) {
                val normalized = normalizeNumTextField(value.text, canHaveOneZero)
                if (normalized != value.text)
                    onValueChange(value.copy(text = normalized))
            }
        }
    )
}