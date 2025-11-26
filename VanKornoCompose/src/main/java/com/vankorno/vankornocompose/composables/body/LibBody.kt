package com.vankorno.vankornocompose.composables.body

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.vankorno.vankornohelpers.values.hideKeyboard

@Composable
fun LibBody(                                             modifier: Modifier,
                                                          content: @Composable ColumnScope.()->Unit,
) = LibBody(modifier, {}, content)



@Composable
fun LibBody(                                             modifier: Modifier,
                                                          onClick: ()->Unit,
                                                          content: @Composable ColumnScope.()->Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    
    Column(
        modifier
            .combinedClickable(
                onClick = {
                    hideKeyboard()
                    onClick()
                },
                interactionSource = interactionSource,
                indication = null
            )
        ,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        content()
    }
}




