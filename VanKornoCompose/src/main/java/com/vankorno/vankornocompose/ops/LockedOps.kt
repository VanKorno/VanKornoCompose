package com.vankorno.vankornocompose.ops

import com.vankorno.vankornodb.api.DbLock
import com.vankorno.vankornohelpers.eLog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

open class LockedOps(                                                        val lock: DbLock
) {
    inline fun <T> get(                                                    default: T,
                                                                           funName: String = "get",
                                                                 crossinline block: ()->T,
    ): T {
        return try {
            lock.withLock { block() }
        } catch (e: Exception) {
            // region LOG
                eLog("LockedOps", "$funName() failed. Details: ${e.message}", e)
            // endregion
            default
        }
    }
    
    
    inline fun <T> exec(                                                  funName: String = "exec",
                                                                crossinline block: ()->T,
    ) {
        get(Unit, funName) { block() }
    }
    
    
    fun async(                                                            funName: String = "async",
                                                                            block: ()->Unit,
    ) {
        CoroutineScope(Dispatchers.Default).launch { exec(funName, block) }
    }
    
    
    suspend inline fun <T> getSusp(                                     default: T,
                                                                        funName: String = "getSusp",
                                                              crossinline block: ()->T,
    ): T = withContext(Dispatchers.Default) {
        get(default, funName) { block() }
    }
    
    
    suspend inline fun <T> susp(                                          funName: String = "susp",
                                                                crossinline block: ()->T,
    ) {
        withContext(Dispatchers.Default) {
            exec(funName) { block() }
        }
    }
    
    
    fun op(funName: String = "op", block: () -> Unit) = Op0(funName, block)
    fun <P1> op(funName: String = "op", block: (P1) -> Unit) = Op1(funName, block)
    fun <P1, P2> op(funName: String = "op", block: (P1, P2) -> Unit) = Op2(funName, block)
    fun <P1, P2, P3> op(funName: String = "op", block: (P1, P2, P3) -> Unit) = Op3(funName, block)
    fun <P1, P2, P3, P4> op(funName: String = "op", block: (P1, P2, P3, P4) -> Unit) = Op4(funName, block)
    fun <P1, P2, P3, P4, P5> op(funName: String = "op", block: (P1, P2, P3, P4, P5) -> Unit) = Op5(funName, block)
    fun <P1, P2, P3, P4, P5, P6> op(funName: String = "op", block: (P1, P2, P3, P4, P5, P6) -> Unit) = Op6(funName, block)
    fun <P1, P2, P3, P4, P5, P6, P7> op(funName: String = "op", block: (P1, P2, P3, P4, P5, P6, P7) -> Unit) = Op7(funName, block)
    fun <P1, P2, P3, P4, P5, P6, P7, P8> op(funName: String = "op", block: (P1, P2, P3, P4, P5, P6, P7, P8) -> Unit) = Op8(funName, block)
    fun <P1, P2, P3, P4, P5, P6, P7, P8, P9> op(funName: String = "op", block: (P1, P2, P3, P4, P5, P6, P7, P8, P9) -> Unit) = Op9(funName, block)
    fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> op(funName: String = "op", block: (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10) -> Unit) = Op10(funName, block)
    
    inner class Op0(private val funName: String, private val block: () -> Unit) : () -> Unit {
        override operator fun invoke() = exec()
        fun exec() = this@LockedOps.exec(funName) { block() }
        fun async() = this@LockedOps.async(funName) { block() }
        suspend fun susp() = this@LockedOps.susp(funName) { block() }
    }
    inner class Op1<P1>(private val funName: String, private val block: (P1) -> Unit) : (P1) -> Unit {
        override operator fun invoke(p1: P1) = exec(p1)
        fun exec(p1: P1) = this@LockedOps.exec(funName) { block(p1) }
        fun async(p1: P1) = this@LockedOps.async(funName) { block(p1) }
        suspend fun susp(p1: P1) = this@LockedOps.susp(funName) { block(p1) }
    }
    inner class Op2<P1, P2>(private val funName: String, private val block: (P1, P2) -> Unit) : (P1, P2) -> Unit {
        override operator fun invoke(p1: P1, p2: P2) = exec(p1, p2)
        fun exec(p1: P1, p2: P2) = this@LockedOps.exec(funName) { block(p1, p2) }
        fun async(p1: P1, p2: P2) = this@LockedOps.async(funName) { block(p1, p2) }
        suspend fun susp(p1: P1, p2: P2) = this@LockedOps.susp(funName) { block(p1, p2) }
    }
    inner class Op3<P1, P2, P3>(private val funName: String, private val block: (P1, P2, P3) -> Unit) : (P1, P2, P3) -> Unit {
        override operator fun invoke(p1: P1, p2: P2, p3: P3) = exec(p1, p2, p3)
        fun exec(p1: P1, p2: P2, p3: P3) = this@LockedOps.exec(funName) { block(p1, p2, p3) }
        fun async(p1: P1, p2: P2, p3: P3) = this@LockedOps.async(funName) { block(p1, p2, p3) }
        suspend fun susp(p1: P1, p2: P2, p3: P3) = this@LockedOps.susp(funName) { block(p1, p2, p3) }
    }
    inner class Op4<P1, P2, P3, P4>(private val funName: String, private val block: (P1, P2, P3, P4) -> Unit) : (P1, P2, P3, P4) -> Unit {
        override operator fun invoke(p1: P1, p2: P2, p3: P3, p4: P4) = exec(p1, p2, p3, p4)
        fun exec(p1: P1, p2: P2, p3: P3, p4: P4) = this@LockedOps.exec(funName) { block(p1, p2, p3, p4) }
        fun async(p1: P1, p2: P2, p3: P3, p4: P4) = this@LockedOps.async(funName) { block(p1, p2, p3, p4) }
        suspend fun susp(p1: P1, p2: P2, p3: P3, p4: P4) = this@LockedOps.susp(funName) { block(p1, p2, p3, p4) }
    }
    inner class Op5<P1, P2, P3, P4, P5>(private val funName: String, private val block: (P1, P2, P3, P4, P5) -> Unit) : (P1, P2, P3, P4, P5) -> Unit {
        override operator fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5) = exec(p1, p2, p3, p4, p5)
        fun exec(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5) = this@LockedOps.exec(funName) { block(p1, p2, p3, p4, p5) }
        fun async(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5) = this@LockedOps.async(funName) { block(p1, p2, p3, p4, p5) }
        suspend fun susp(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5) = this@LockedOps.susp(funName) { block(p1, p2, p3, p4, p5) }
    }
    inner class Op6<P1, P2, P3, P4, P5, P6>(private val funName: String, private val block: (P1, P2, P3, P4, P5, P6) -> Unit) : (P1, P2, P3, P4, P5, P6) -> Unit {
        override operator fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6) = exec(p1, p2, p3, p4, p5, p6)
        fun exec(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6) = this@LockedOps.exec(funName) { block(p1, p2, p3, p4, p5, p6) }
        fun async(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6) = this@LockedOps.async(funName) { block(p1, p2, p3, p4, p5, p6) }
        suspend fun susp(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6) = this@LockedOps.susp(funName) { block(p1, p2, p3, p4, p5, p6) }
    }
    inner class Op7<P1, P2, P3, P4, P5, P6, P7>(private val funName: String, private val block: (P1, P2, P3, P4, P5, P6, P7) -> Unit) : (P1, P2, P3, P4, P5, P6, P7) -> Unit {
        override operator fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7) = exec(p1, p2, p3, p4, p5, p6, p7)
        fun exec(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7) = this@LockedOps.exec(funName) { block(p1, p2, p3, p4, p5, p6, p7) }
        fun async(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7) = this@LockedOps.async(funName) { block(p1, p2, p3, p4, p5, p6, p7) }
        suspend fun susp(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7) = this@LockedOps.susp(funName) { block(p1, p2, p3, p4, p5, p6, p7) }
    }
    inner class Op8<P1, P2, P3, P4, P5, P6, P7, P8>(private val funName: String, private val block: (P1, P2, P3, P4, P5, P6, P7, P8) -> Unit) : (P1, P2, P3, P4, P5, P6, P7, P8) -> Unit {
        override operator fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8) = exec(p1, p2, p3, p4, p5, p6, p7, p8)
        fun exec(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8) = this@LockedOps.exec(funName) { block(p1, p2, p3, p4, p5, p6, p7, p8) }
        fun async(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8) = this@LockedOps.async(funName) { block(p1, p2, p3, p4, p5, p6, p7, p8) }
        suspend fun susp(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8) = this@LockedOps.susp(funName) { block(p1, p2, p3, p4, p5, p6, p7, p8) }
    }
    inner class Op9<P1, P2, P3, P4, P5, P6, P7, P8, P9>(private val funName: String, private val block: (P1, P2, P3, P4, P5, P6, P7, P8, P9) -> Unit) : (P1, P2, P3, P4, P5, P6, P7, P8, P9) -> Unit {
        override operator fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9) = exec(p1, p2, p3, p4, p5, p6, p7, p8, p9)
        fun exec(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9) = this@LockedOps.exec(funName) { block(p1, p2, p3, p4, p5, p6, p7, p8, p9) }
        fun async(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9) = this@LockedOps.async(funName) { block(p1, p2, p3, p4, p5, p6, p7, p8, p9) }
        suspend fun susp(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9) = this@LockedOps.susp(funName) { block(p1, p2, p3, p4, p5, p6, p7, p8, p9) }
    }
    inner class Op10<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10>(private val funName: String, private val block: (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10) -> Unit) : (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10) -> Unit {
        override operator fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10) = exec(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10)
        fun exec(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10) = this@LockedOps.exec(funName) { block(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10) }
        fun async(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10) = this@LockedOps.async(funName) { block(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10) }
        suspend fun susp(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10) = this@LockedOps.susp(funName) { block(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10) }
    }
}
