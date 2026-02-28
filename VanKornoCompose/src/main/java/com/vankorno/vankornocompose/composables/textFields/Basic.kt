package com.vankorno.vankornocompose.composables.textFields

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.TextUnit
import com.vankorno.vankornocompose.actions.applyIf
import com.vankorno.vankornocompose.values.MOD_StandardTextField
import com.vankorno.vankornocompose.vm.VmText
import com.vankorno.vankornocompose.vm.collect
import com.vankorno.vankornohelpers.values.LibColors.BlackT30

@Composable
fun LibBasicTextField(                     vmText: VmText,
                                         modifier: Modifier = Modifier,
                                 contentAlignment: Alignment = Alignment.CenterStart,
                                          enabled: Boolean = true,
                                         readOnly: Boolean = false,
                                   lineQuantRange: IntRange = 1..Int.MAX_VALUE,
                                        textStyle: TextStyle = TextStyle.Default,
                                             hint: String = "",
                                  keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
                                  keyboardActions: KeyboardActions = KeyboardActions.Default,
                             visualTransformation: VisualTransformation = VisualTransformation.None,
                                      cursorBrush: Brush = SolidColor(Color.Black),
                              enableFocusHandling: Boolean = true,
                                    normalizeText: (String)->String = { it },
                                interactionSource: MutableInteractionSource? = null,
                                       decorModif: Modifier = MOD_StandardTextField,
                           wrapSelectionContainer: Boolean = true,
                                     onTextLayout: (TextLayoutResult)->Unit = {},
                                            decor: @Composable BoxScope.()->Unit = {},
) {
    val value by vmText.state()
    
    val requester = remember { FocusRequester() }
    
    vmText.focusRequest.collect { requester.requestFocus() }
    vmText.clearFocusRequest.collect { requester.freeFocus() }
    
    val combinedModifier = modifier.applyIf(enableFocusHandling) {
        focusRequester(requester)
            .onFocusChanged {
                vmText.hasFocus.value = it.isFocused
                if (!it.isFocused) {
                    val normalized = normalizeText(vmText.text)
                    if (normalized != vmText.text) {
                        vmText.text = normalized
                    }
                }
            }
    }
    
    val textFieldContent: @Composable ()->Unit = {
        BasicTextField(
            value = value,
            onValueChange = vmText::updateFrom,
            modifier = combinedModifier,
            enabled = enabled,
            readOnly = readOnly,
            textStyle = textStyle,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = lineQuantRange.first == 1 && lineQuantRange.last == 1,
            minLines = lineQuantRange.first,
            maxLines = lineQuantRange.last,
            visualTransformation = visualTransformation,
            onTextLayout = onTextLayout,
            interactionSource = interactionSource ?: remember { MutableInteractionSource() },
            cursorBrush = cursorBrush,
            decorationBox = { innerTextField ->
                Box(
                    modifier = decorModif,
                    contentAlignment = contentAlignment
                ) {
                    innerTextField()
                    
                    if (value.text.isEmpty()  &&  hint.isNotEmpty()) {
                        HintText(hint, textStyle.fontSize)
                    }
                    decor()
                }
            }
        )
    }
    
    if (wrapSelectionContainer)
        SelectionContainer { textFieldContent() }
    else
        textFieldContent()
}


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