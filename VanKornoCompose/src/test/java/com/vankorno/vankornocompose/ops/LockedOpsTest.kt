package com.vankorno.vankornocompose.ops

import com.vankorno.vankornodb.api.DbLock
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean

class LockedOpsTest {
    private lateinit var lock: DbLock
    private lateinit var localOps: LockedOps
    
    @Before
    fun setUp() {
        lock = DbLock()
        localOps = LockedOps(lock)
    }
    
    @Test
    fun `get returns computed value when block succeeds`() {
        val result = localOps.get(default = -1, funName = "testSuccess") {
            40 + 2
        }
        assertEquals(42, result)
    }
    
    @Test
    fun `get returns default value when block throws`() {
        val result = localOps.get(default = "fallback", funName = "testFail") {
            throw IllegalStateException("Simulated failure")
        }
        assertEquals("fallback", result)
    }
    
    @Test
    fun `exec executes block successfully`() {
        var executed = false
        localOps.exec("testExec") {
            executed = true
        }
        assertTrue(executed)
    }
    
    @Test
    fun `exec catches exception without rethrowing`() {
        var reached = false
        localOps.exec("testExecError") {
            reached = true
            throw RuntimeException("Simulated error in exec")
        }
        assertTrue(reached)
    }
    
    @Test
    fun `async executes block on a background dispatcher`() {
        val latch = CountDownLatch(1)
        val executed = AtomicBoolean(false)
        
        localOps.async("testAsync") {
            executed.set(true)
            latch.countDown()
        }
        
        val completed = latch.await(2, TimeUnit.SECONDS)
        assertTrue(completed)
        assertTrue(executed.get())
    }
    
    @Test
    fun `async catches exception without crashing`() {
        val latch = CountDownLatch(1)
        
        localOps.async("testAsyncError") {
            try {
                throw RuntimeException("Simulated error in async")
            } finally {
                latch.countDown()
            }
        }
        
        val completed = latch.await(2, TimeUnit.SECONDS)
        assertTrue(completed)
    }
    
    @Test
    fun `getSusp returns value in coroutine`() = runBlocking {
        val result = localOps.getSusp(default = "default", funName = "testGetSusp") {
            "success"
        }
        assertEquals("success", result)
    }
    
    @Test
    fun `getSusp returns default on exception in coroutine`() = runBlocking {
        val result = localOps.getSusp(default = "default", funName = "testGetSuspError") {
            throw RuntimeException("Simulated error")
        }
        assertEquals("default", result)
    }
    
    @Test
    fun `susp executes block in coroutine`() = runBlocking {
        var count = 0
        localOps.susp("testSusp") {
            count += 5
        }
        assertEquals(5, count)
    }
    
    
    // region Bundled operations (Op0 .. Op10) tests
    
    @Test
    fun `op Op0 invoke and exec execute block`() {
        var count = 0
        val op = localOps.op("inc") { count++ }

        op() // operator invoke
        assertEquals(1, count)

        op.exec()
        assertEquals(2, count)
    }

    @Test
    fun `op Op0 async executes in background`() {
        val latch = CountDownLatch(1)
        val executed = AtomicBoolean(false)

        val op = localOps.op("asyncOp") {
            executed.set(true)
            latch.countDown()
        }

        op.async()
        val completed = latch.await(2, TimeUnit.SECONDS)
        assertTrue(completed)
        assertTrue(executed.get())
    }

    @Test
    fun `op Op0 susp executes in coroutine`() = runBlocking {
        var flag = false
        val op = localOps.op("suspOp") { flag = true }

        op.susp()
        assertTrue(flag)
    }

    @Test
    fun `op Op0 can be passed directly as function type lambda`() {
        var clicked = false
        val clickOp = localOps.op("click") { clicked = true }

        fun simulateButton(onClick: () -> Unit) = onClick()
        simulateButton(clickOp)

        assertTrue(clicked)
    }

    @Test
    fun `op Op1 passes argument correctly across invoke exec async and susp`() = runBlocking {
        val received = mutableListOf<String>()
        val op = localOps.op("op1") { str: String -> received.add(str) }

        op("first")
        op.exec("second")
        op.susp("third")

        assertEquals(listOf("first", "second", "third"), received)

        val latch = CountDownLatch(1)
        val asyncOp = localOps.op("op1Async") { str: String ->
            received.add(str)
            latch.countDown()
        }
        asyncOp.async("fourth")
        assertTrue(latch.await(2, TimeUnit.SECONDS))
        assertEquals(listOf("first", "second", "third", "fourth"), received)
    }

    @Test
    fun `op Op2 passes two arguments correctly`() {
        var sum = 0
        val addOp = localOps.op("add") { a: Int, b: Int -> sum = a + b }

        addOp(15, 27)
        assertEquals(42, sum)
    }

    @Test
    fun `op Op3 passes three arguments correctly`() {
        var result = ""
        val joinOp = localOps.op("join3") { a: String, b: Int, c: Boolean ->
            result = "$a-$b-$c"
        }

        joinOp("val", 10, true)
        assertEquals("val-10-true", result)
    }

    @Test
    fun `op Op10 passes all ten arguments correctly`() {
        var result = 0
        val sumOp = localOps.op("sum10") {
            p1: Int, p2: Int, p3: Int, p4: Int, p5: Int,
            p6: Int, p7: Int, p8: Int, p9: Int, p10: Int ->
            result = p1 + p2 + p3 + p4 + p5 + p6 + p7 + p8 + p9 + p10
        }

        sumOp(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        assertEquals(55, result)
    }

    @Test
    fun `op catches exception inside block without crashing`() {
        val op = localOps.op("failingOp") {
            throw IllegalStateException("Op failure")
        }

        // Should not throw
        op()
        op.exec()
    }

    // endregion
}
