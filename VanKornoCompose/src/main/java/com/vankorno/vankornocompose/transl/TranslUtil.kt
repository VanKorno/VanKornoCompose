package com.vankorno.vankornocompose.transl

import com.vankorno.vankornocompose.transl.data.LanguagesWithFemForms
import com.vankorno.vankornocompose.transl.data.TrWeekDayLetters
import com.vankorno.vankornocompose.values.LibGlobals2
import com.vankorno.vankornohelpers.values.LibConstants.ENG
import com.vankorno.vankornohelpers.values.LibConstants.LangAuto
import java.util.Locale

class TranslUtil {
    
    fun getActuallyUsedLang(                                     lang: String = LibGlobals2.language
    ): String = when (lang) {
                    LangAuto -> Locale.getDefault().language
                    else -> lang
                }
    
    
    fun isLangWithFemForms(                                      lang: String = LibGlobals2.language
    ): Boolean {
        val language = getActuallyUsedLang()
        return LanguagesWithFemForms.value.contains(language)
    }
    
    
    
    fun getWeekDayLetters() = defaultToEng(TrWeekDayLetters.value, getActuallyUsedLang())
    
    private fun defaultToEng(                               transl: Map<String, Lazy<List<String>>>,
                                                          language: String,
    ) = if (translAbsent(transl, language))
        transl[ENG]?.value ?: emptyList()
    else
        transl[language]?.value ?: emptyList()
    
    
    private fun translAbsent(                               transl: Map<String, Lazy<List<String>>>,
                                                          language: String,
    ) = transl[language]?.value.isNullOrEmpty()
    
    
    
    
}