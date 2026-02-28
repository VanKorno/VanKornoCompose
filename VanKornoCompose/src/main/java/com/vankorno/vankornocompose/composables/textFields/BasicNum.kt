package com.vankorno.vankornocompose.composables.textFields

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import com.vankorno.vankornocompose.values.MOD_StandardTextField
import com.vankorno.vankornocompose.vm.VmTextNum
import com.vankorno.vankornocompose.vm.normalizeNumTextField

@Composable
fun LibBasicNumTextField(                  vmText: VmTextNum,
                                         modifier: Modifier = Modifier,
                                 contentAlignment: Alignment = Alignment.CenterStart,
                                          enabled: Boolean = true,
                                         readOnly: Boolean = false,
                                        textStyle: TextStyle = TextStyle.Default,
                                  keyboardActions: KeyboardActions = KeyboardActions.Default,
                              enableFocusHandling: Boolean = true,
                           wrapSelectionContainer: Boolean = true,
                             visualTransformation: VisualTransformation = VisualTransformation.None,
                                     onTextLayout: (TextLayoutResult)->Unit = {},
                                interactionSource: MutableInteractionSource? = null,
                                      cursorBrush: Brush = SolidColor(Color.Black),
                                   canHaveOneZero: Boolean = false,
                                       decorModif: Modifier = MOD_StandardTextField,
                                            decor: @Composable BoxScope.()->Unit = { },
) {
    LibBasicTextField(
        vmText = vmText,
        modifier = modifier,
        contentAlignment = contentAlignment,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        keyboardActions = keyboardActions,
        lineQuantRange = 1..1,
        enableFocusHandling = enableFocusHandling,
        wrapSelectionContainer = wrapSelectionContainer,
        visualTransformation = visualTransformation,
        onTextLayout = onTextLayout,
        interactionSource = interactionSource,
        cursorBrush = cursorBrush,
        decorModif = decorModif,
        decor = decor,
        normalizeText = { input ->
            normalizeNumTextField(input, canHaveOneZero)
        },
        hint = "0",
    )
}