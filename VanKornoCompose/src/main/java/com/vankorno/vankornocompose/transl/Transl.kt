package com.vankorno.vankornocompose.transl

import com.vankorno.vankornocompose.values.LibGlobals2
import com.vankorno.vankornohelpers.values.LibLangConst.ENG

/**
 * Only need to pass lang from the Flow on the same screen where the language picker is.
 */
fun translate(                                              transl: Lazy<Map<String, Lazy<String>>>,
                                                              lang: String = LibGlobals2.language,
): String {
    val language = TranslUtil().getActuallyUsedLang(lang)
    return defaultToEng(transl.value, language)
}



private fun defaultToEng(                                         transl: Map<String, Lazy<String>>,
                                                                language: String,
) = if (translAbsent(transl, language))
        transl[ENG]?.value ?: ""
    else
        transl[language]?.value ?: ""


private fun translAbsent(                                         transl: Map<String, Lazy<String>>,
                                                                language: String,
) = transl[language]?.value.isNullOrEmpty()











