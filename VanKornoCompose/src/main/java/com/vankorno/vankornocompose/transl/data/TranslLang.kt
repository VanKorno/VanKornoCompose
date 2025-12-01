package com.vankorno.vankornocompose.transl.data

import com.vankorno.vankornohelpers.values.LibLangConst.ENG
import com.vankorno.vankornohelpers.values.LibLangConst.LangAuto
import com.vankorno.vankornohelpers.values.LibLangConst.UKR

/* https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes */

enum class TranslLang(                                                          val code: String,
                                                                              val uiName: String,
                                                                    val translatedUiName: String,
) {
    Auto(LangAuto, "Auto", "Auto"),
    Eng(ENG, "English", "English"),
    Ukr(UKR, "Ukrainian", "Українська"),
    //Spanish(SPA, "Spanish", "Español"),
}

