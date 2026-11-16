package com.vankorno.vankornocompose.vm

import androidx.lifecycle.SavedStateHandle
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class VmMapTest {

    @Test
    fun testVmMapBasicOperations() {
        val vmMap = VmMap(mapOf("a" to 1))
        assertEquals(mapOf("a" to 1), vmMap.value)

        vmMap.put("b", 2)
        assertEquals(mapOf("a" to 1, "b" to 2), vmMap.value)

        vmMap.putAll(mapOf("c" to 3, "d" to 4))
        assertEquals(mapOf("a" to 1, "b" to 2, "c" to 3, "d" to 4), vmMap.value)

        vmMap.remove("a")
        assertEquals(mapOf("b" to 2, "c" to 3, "d" to 4), vmMap.value)

        vmMap.remove(listOf("b", "c"))
        assertEquals(mapOf("d" to 4), vmMap.value)

        assertEquals(4, vmMap["d"])
        assertNull(vmMap["a"])

        val computed = vmMap.getOrPut("e") { 5 }
        assertEquals(5, computed)
        assertEquals(mapOf("d" to 4, "e" to 5), vmMap.value)

        vmMap.updateKey("e") { it?.let { it * 10 } }
        assertEquals(50, vmMap["e"])

        vmMap.updateKey("nonexistent") { 100 }
        assertEquals(100, vmMap["nonexistent"])

        vmMap.updateKey("d") { null }
        assertNull(vmMap["d"])

        vmMap.clear()
        assertTrue(vmMap.value.isEmpty())

        vmMap.put("x", 10)
        vmMap.reset()
        assertEquals(mapOf("a" to 1), vmMap.value)
    }

    @Test
    fun testVmSavedMap() {
        val ssh = SavedStateHandle()
        val savedMap = VmSavedMap(ssh, "saved_map_key", mapOf("key1" to "val1"))

        assertEquals(mapOf("key1" to "val1"), savedMap.value)

        savedMap.put("key2", "val2")
        assertEquals(mapOf("key1" to "val1", "key2" to "val2"), savedMap.value)
        assertEquals(mapOf("key1" to "val1", "key2" to "val2"), ssh["saved_map_key"])

        savedMap.remove("key1")
        assertEquals(mapOf("key2" to "val2"), savedMap.value)
        assertEquals(mapOf("key2" to "val2"), ssh["saved_map_key"])
    }
}
