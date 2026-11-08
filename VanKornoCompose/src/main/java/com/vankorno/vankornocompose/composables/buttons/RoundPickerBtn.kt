package com.vankorno.vankornocompose.composables.buttons

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.vankorno.vankornocompose.composables.barBot.SmallRoundBtnBorderW
import com.vankorno.vankornocompose.dp2
import com.vankorno.vankornocompose.dp4
import com.vankorno.vankornocompose.sp1
import com.vankorno.vankornocompose.sp2
import com.vankorno.vankornohelpers.values.LibColors.GreyText
import com.vankorno.vankornohelpers.values.LibColors.PlainBlack

@Composable
fun RoundPickerBtnBase(                                                   text: String,
                                                                        picked: Boolean,
                                                                         color: Int = PlainBlack,
                                                                   borderColor: Int = GreyText,
                                                                       btnSize: Dp = 42.dp2(),
                                                                      fontSize: TextUnit = 18.sp1(),
                                                                         paddH: Dp = 1.dp2(),
                                                                   onLongClick: ()->Unit = {},
                                                                       onClick: ()->Unit,
) {
    var backgroundColor = Color.Transparent
    var borderCol = Color.Transparent
    if (picked) {
        backgroundColor = Color(color)
        borderCol = Color(borderColor)
    }
    val modifier = Modifier.size(btnSize).padding(horizontal = paddH)
    
    RoundBtn(
        modifier = modifier,
        backgroundColor = backgroundColor,
        borderColor = borderCol,
        borderWidth = SmallRoundBtnBorderW,
        enabled = true,
        onLongClick = onLongClick,
        onClick = onClick
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            textAlign = TextAlign.Center
        )
    }
}


@Composable
fun RoundPickerBtn(                                                          text: String,
                                                                           picked: Boolean,
                                                                            color: Int = PlainBlack,
                                                                      borderColor: Int = GreyText,
                                                                            paddH: Dp = 1.dp2(),
                                                                      onLongClick: ()->Unit = {},
                                                                          onClick: ()->Unit,
) = RoundPickerBtnBase(
    text = text,
    picked = picked,
    color = color,
    borderColor = borderColor,
    btnSize = 60.dp4(),
    fontSize = 20.sp2(),
    paddH = paddH,
    onLongClick = onLongClick,
    onClick = onClick
)


@Composable
fun RoundPickerBtnSmall(                                                     text: String,
                                                                           picked: Boolean,
                                                                            color: Int = PlainBlack,
                                                                      borderColor: Int = GreyText,
                                                                            paddH: Dp = 1.dp,
                                                                      onLongClick: ()->Unit = {},
                                                                          onClick: ()->Unit,
) = RoundPickerBtnBase(
    text = text,
    picked = picked,
    color = color,
    borderColor = borderColor,
    btnSize = 37.dp2(),
    fontSize = 16.sp1(),
    paddH = paddH,
    onLongClick = onLongClick,
    onClick = onClick
)
