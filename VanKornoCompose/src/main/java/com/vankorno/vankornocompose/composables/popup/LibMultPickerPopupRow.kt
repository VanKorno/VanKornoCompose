package com.vankorno.vankornocompose.composables.popup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vankorno.vankornocompose.sp1
import com.vankorno.vankornocompose.values.MOD_MaxW
import com.vankorno.vankornocompose.values.popupOFF
import com.vankorno.vankornohelpers.LibUI

@Composable
fun LibMultPickerPopupRow(                                                      isPicked: Boolean,
                                                                                     txt: String,
                                                                                 onClick: ()->Unit,
) {
    Row(
        MOD_MaxW
            .padding(vertical=12.dp)
            .clickable {
                onClick()
                popupOFF()
            }
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        Icon(
            modifier = Modifier.size(30.dp),
            painter = painterResource(id = LibUI().getCircleIcon(isPicked)),
            tint = Color.White,
            contentDescription = null,
        )
        Text(
            modifier = Modifier.padding(start=16.dp),
            text = txt,
            color = Color.White,
            fontSize = 22.sp1(),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
        )
    }
}