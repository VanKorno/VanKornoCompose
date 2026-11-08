package com.vankorno.vankornocompose.composables.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.vankorno.vankornocompose.actions.applyIf
import com.vankorno.vankornocompose.actions.tweakTransparency
import com.vankorno.vankornocompose.composables.barBot.MOD_SmallRoundBtnMedium
import com.vankorno.vankornocompose.composables.barBot.RoundBtnBorderW
import com.vankorno.vankornocompose.composables.barBot.SmallRoundBtnBorderW
import com.vankorno.vankornocompose.theme_main.LibColor
import com.vankorno.vankornohelpers.values.LibColors.BlackT30

@Composable
fun RoundBtn(                                      modifier: Modifier = Modifier,
                                            backgroundColor: Color = Color.Black,
                                                borderColor: Color = LibColor.BlackSurfBorder.color,
                                                borderWidth: Dp = RoundBtnBorderW,
                                                    enabled: Boolean = true,
                                                    testTag: String = "",
                                                onLongClick: ()->Unit = {},
                                                    onClick: ()->Unit,
                                                    content: @Composable BoxScope.()->Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }

    BoxWithConstraints(modifier = modifier) {
        val diameter = minOf(maxWidth, maxHeight)
        Box(
            modifier = Modifier
                .size(diameter)
                .background(backgroundColor, CircleShape)
                .border(borderWidth, borderColor, CircleShape)
                .combinedClickable(
                    enabled = enabled,
                    onClick = onClick,
                    onLongClick = onLongClick,
                    interactionSource = interactionSource,
                    indication = null
                )
                .applyIf(testTag.isNotBlank()) {
                    testTag(testTag)
                },
            contentAlignment = Alignment.Center,
            content = content
        )
    }
}


@Composable
fun RoundBtnSmall(                                                     enabled: Boolean,
                                                                          icon: Int,
                                                                       icColor: Color = Color.White,
                                                                       onClick: ()->Unit,
) {
    var backgroundColor = Color.Black
    var borderColor = LibColor.BlackSurfBorder.color
    var iconColor = icColor
    
    if (!enabled) {
        backgroundColor = Color(BlackT30)
        borderColor = LibColor.BlackSurfBorderDisabled.color
        iconColor = icColor.tweakTransparency(0.1f)
    }
    
    val modifier = MOD_SmallRoundBtnMedium
    
    RoundBtn(
        modifier = modifier,
        backgroundColor = backgroundColor,
        borderColor = borderColor,
        borderWidth = SmallRoundBtnBorderW,
        enabled = enabled,
        onClick = onClick
    ) {
        Icon(
            modifier = Modifier
                .matchParentSize()
                .padding(7.dp),
            painter = painterResource(id = icon),
            tint = iconColor,
            contentDescription = null
        )
    }
}
