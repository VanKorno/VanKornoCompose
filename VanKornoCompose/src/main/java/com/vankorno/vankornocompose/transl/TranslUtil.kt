package com.vankorno.vankornocompose.transl

import com.vankorno.vankornocompose.transl.data.LanguagesWithFemForms
import com.vankorno.vankornocompose.transl.data.TrWeekDayLetters
import com.vankorno.vankornocompose.transl.data.TranslLang
import com.vankorno.vankornocompose.values.LibGlobals2.language
import com.vankorno.vankornohelpers.values.LibLangConst.ENG
import com.vankorno.vankornohelpers.values.LibLangConst.LangAuto
import java.util.Locale

class TranslUtil {
    
    fun getActuallyUsedLang(                                           lang: String = language.value
    ): String = when (lang) {
                    LangAuto -> Locale.getDefault().language
                    else -> lang
                }
    
    
    fun isLangWithFemForms(                                            lang: String = language.value
    ): Boolean {
        val actualLang = getActuallyUsedLang(lang)
        return LanguagesWithFemForms.value.contains(actualLang)
    }
    
    
    
    fun getWeekDayLetters() = defaultToEng(TrWeekDayLetters.value, getActuallyUsedLang())
    
    
    private fun defaultToEng(                               transl: Map<String, Lazy<List<String>>>,
                                                              lang: String,
    ) = if (translAbsent(transl, lang))
        transl[ENG]?.value ?: emptyList()
    else
        transl[lang]?.value ?: emptyList()
    
    
    private fun translAbsent(                               transl: Map<String, Lazy<List<String>>>,
                                                              lang: String,
    ) = transl[lang]?.value.isNullOrEmpty()
    
    
    
    fun getLangUiTextByLocale(localeCode: String) = TranslLang.entries.find { it.code == localeCode }?.uiName ?: ""
    
    
    
    
}