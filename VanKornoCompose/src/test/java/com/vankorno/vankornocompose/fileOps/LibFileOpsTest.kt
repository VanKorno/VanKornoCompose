package com.vankorno.vankornocompose.fileOps

import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class LibFileOpsTest {
    
    @Test
    fun `generateUniqueFilename format is correct`() {
        val name = LibFileOps.generateUniqueFilename("test", "txt")
        
        assertTrue(name.startsWith("test__"))
        assertTrue(name.endsWith(".txt"))
    }
    
    @Test
    fun `getMimeType returns null for no extension`() {
        val type = LibFileOps.getMimeType("a")
        
        assertNull(type)
    }
    
}