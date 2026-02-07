package com.vankorno.vankornocompose.transl

import com.vankorno.vankornocompose.values.LibGlobals2.language
import com.vankorno.vankornohelpers.values.LibLangConst.ENG

/**
 * Only need to pass lang from the Flow on the same screen where the language picker is.
 */
fun translate(                                              transl: Lazy<Map<String, Lazy<String>>>,
                                                              lang: String = language.value,
): String {
    val actualLang = TranslUtil().getActuallyUsedLang(lang)
    return defaultToEng(transl.value, actualLang)
}



private fun defaultToEng(                                         transl: Map<String, Lazy<String>>,
                                                                    lang: String,
) = if (translAbsent(transl, lang))
        transl[ENG]?.value ?: ""
    else
        transl[lang]?.value ?: ""


private fun translAbsent(                                         transl: Map<String, Lazy<String>>,
                                                                    lang: String,
) = transl[lang]?.value.isNullOrEmpty()











