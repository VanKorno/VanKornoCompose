package com.vankorno.vankornocompose.composables.popup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.vankorno.vankornocompose.dp5
import com.vankorno.vankornocompose.transl.data.TranslLang
import com.vankorno.vankornocompose.transl.data.excludedTranslLangs
import com.vankorno.vankornocompose.values.LocalLanguage
import com.vankorno.vankornocompose.values.MOD_MaxW

@Composable
fun LibPopLangPicker(                                                       onClick: (String)->Unit
) {
    Column(
        MOD_MaxW
            .padding(
                vertical = 18.dp5(),
                horizontal = 18.dp
            )
        ,
        verticalArrangement = Arrangement.Top
    ) {
        TranslLang.entries.forEach { enumElement ->
            if (!excludedTranslLangs.contains(enumElement)) {
                val txt = enumElement.uiName
                val langCode = enumElement.code
                val isPicked = LocalLanguage.current == langCode
                
                LibMultPickerPopupRow(isPicked, txt) {
                    onClick(langCode)
                }
            }
        }
    }
}