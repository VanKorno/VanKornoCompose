package com.vankorno.vankornocompose.transl

import com.vankorno.vankornohelpers.values.LibConstants.UKR
import org.junit.Assert.assertTrue
import org.junit.Test

class TranslUtilTest {
    
    @Test
    fun `isLangWithFemForms returns true for a lang with femForms`() {
        assertTrue(TranslUtil().isLangWithFemForms(UKR))
    }
    
    
    
    
    
}