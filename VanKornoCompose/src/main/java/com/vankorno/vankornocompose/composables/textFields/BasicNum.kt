package com.vankorno.vankornocompose.composables.textFields

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import com.vankorno.vankornocompose.vm.VmEvent
import com.vankorno.vankornocompose.vm.VmVal
import com.vankorno.vankornocompose.vm.normalizeNumTextField

@Composable
fun LibBasicNumTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = TextStyle.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    interactionSource: MutableInteractionSource? = null,
    cursorBrush: Brush = SolidColor(Color.Black),
    enableFocusHandling: Boolean = true,
    hasFocus: VmVal<Boolean>? = null,
    focusRequest: VmEvent? = null,
    clearFocusRequest: VmEvent? = null,
    wrapSelectionContainer: Boolean = true,
    canHaveOneZero: Boolean = false,
    decorationBox: @Composable (innerTextField: @Composable () -> Unit) -> Unit = { it() }
) {
    var internalValue by remember { mutableStateOf(value) }

    LibBasicTextField(
        value = internalValue,
        onValueChange = {
            internalValue = it
            onValueChange(it)
        },
        modifier = modifier,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        keyboardActions = keyboardActions,
        singleLine = true,
        maxLines = 1,
        minLines = 1,
        visualTransformation = visualTransformation,
        onTextLayout = onTextLayout,
        interactionSource = interactionSource,
        cursorBrush = cursorBrush,
        enableFocusHandling = enableFocusHandling,
        hasFocus = hasFocus,
        focusRequest = focusRequest,
        clearFocusRequest = clearFocusRequest,
        wrapSelectionContainer = wrapSelectionContainer,
        decorationBox = decorationBox
    )

    // normalize number on focus loss
    LaunchedEffect(hasFocus, internalValue) {
        snapshotFlow { hasFocus?.value ?: false }
            .collect { focused ->
                if (!focused) {
                    val normalized = normalizeNumTextField(internalValue, canHaveOneZero)
                    if (normalized != internalValue) {
                        internalValue = normalized
                        onValueChange(normalized)
                    }
                }
            }
    }
}