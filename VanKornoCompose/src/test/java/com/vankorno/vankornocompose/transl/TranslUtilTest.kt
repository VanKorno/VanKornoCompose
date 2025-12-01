package com.vankorno.vankornocompose.transl

import com.vankorno.vankornocompose.transl.data.TranslLang
import com.vankorno.vankornohelpers.values.LibLangConst.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class TranslUtilTest {
    
    @Test
    fun `isLangWithFemForms returns true for a lang with femForms`() {
        assertTrue(TranslUtil().isLangWithFemForms(UKR))
    }
    
    
    @Test
    fun `getLangUiTextByLocale with normal languages`() {
        assertEquals("Auto", TranslUtil().getLangUiTextByLocale(LangAuto))
        assertEquals(TranslLang.Auto.uiName, TranslUtil().getLangUiTextByLocale(LangAuto))
        
        assertEquals(TranslLang.Eng.uiName, TranslUtil().getLangUiTextByLocale(ENG))
        assertEquals(TranslLang.Ukr.uiName, TranslUtil().getLangUiTextByLocale(UKR))
    }
    
    
    @Test
    fun `getLangUiTextByLocale returns empty str for an empty code str`() {
        assertEquals("", TranslUtil().getLangUiTextByLocale(""))
    }
    
    @Test
    fun `getLangUiTextByLocale returns empty str for non valid code str`() {
        assertEquals("", TranslUtil().getLangUiTextByLocale("ru"))
        assertEquals("", TranslUtil().getLangUiTextByLocale(UsLangTag))
        
    }
    
    
    
}