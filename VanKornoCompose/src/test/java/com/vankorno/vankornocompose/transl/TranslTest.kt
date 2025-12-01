package com.vankorno.vankornocompose.transl

import com.vankorno.vankornocompose.transl.translated.TrDelete
import com.vankorno.vankornohelpers.values.LibLangConst.ENG
import com.vankorno.vankornohelpers.values.LibLangConst.UKR
import org.junit.Assert.assertEquals
import org.junit.Test

class TranslTest {
    
    @Test
    fun `getTransl returns correct translation`() {
        assertEquals("Delete", translate(TrDelete, ENG))
        assertEquals("Видалити", translate(TrDelete, UKR))
    }
    @Test
    fun `getTransl defaults to ENG`() {
        assertEquals("Delete", translate(TrDelete, ""))
        assertEquals("Delete", translate(TrDelete, "ru"))
        assertEquals("Delete", translate(TrDelete, "yo"))
    }
}