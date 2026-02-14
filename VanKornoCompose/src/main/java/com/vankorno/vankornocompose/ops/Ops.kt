package com.vankorno.vankornocompose.ops

import com.vankorno.vankornodb.api.DbLock


class ExecOp0(lock: DbLock, private val block: () -> Unit) : BaseLockedOp(lock) {
    operator fun invoke() = exec()
    fun exec() = execLocked(block)
    fun async() = execAsyncLocked(block)
    suspend fun susp() = execSuspLocked(block)
}

class ExecOp1<P1>(lock: DbLock, private val block: (P1) -> Unit) : BaseLockedOp(lock) {
    operator fun invoke(p1: P1) = exec(p1)
    fun exec(p1: P1) = execLocked { block(p1) }
    fun async(p1: P1) = execAsyncLocked { block(p1) }
    suspend fun susp(p1: P1) = execSuspLocked { block(p1) }
}

class ExecOp2<P1, P2>(lock: DbLock, private val block: (P1, P2) -> Unit) : BaseLockedOp(lock) {
    operator fun invoke(p1: P1, p2: P2) = exec(p1, p2)
    fun exec(p1: P1, p2: P2) = execLocked { block(p1, p2) }
    fun async(p1: P1, p2: P2) = execAsyncLocked { block(p1, p2) }
    suspend fun susp(p1: P1, p2: P2) = execSuspLocked { block(p1, p2) }
}

class ExecOp3<P1, P2, P3>(lock: DbLock, private val block: (P1, P2, P3) -> Unit) : BaseLockedOp(lock) {
    operator fun invoke(p1: P1, p2: P2, p3: P3) = exec(p1, p2, p3)
    fun exec(p1: P1, p2: P2, p3: P3) = execLocked { block(p1, p2, p3) }
    fun async(p1: P1, p2: P2, p3: P3) = execAsyncLocked { block(p1, p2, p3) }
    suspend fun susp(p1: P1, p2: P2, p3: P3) = execSuspLocked { block(p1, p2, p3) }
}

class ExecOp4<P1, P2, P3, P4>(lock: DbLock, private val block: (P1, P2, P3, P4) -> Unit) : BaseLockedOp(lock) {
    operator fun invoke(p1: P1, p2: P2, p3: P3, p4: P4) = exec(p1, p2, p3, p4)
    fun exec(p1: P1, p2: P2, p3: P3, p4: P4) = execLocked { block(p1, p2, p3, p4) }
    fun async(p1: P1, p2: P2, p3: P3, p4: P4) = execAsyncLocked { block(p1, p2, p3, p4) }
    suspend fun susp(p1: P1, p2: P2, p3: P3, p4: P4) = execSuspLocked { block(p1, p2, p3, p4) }
}

class ExecOp5<P1, P2, P3, P4, P5>(lock: DbLock, private val block: (P1, P2, P3, P4, P5) -> Unit) : BaseLockedOp(lock) {
    operator fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5) = exec(p1, p2, p3, p4, p5)
    fun exec(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5) = execLocked { block(p1, p2, p3, p4, p5) }
    fun async(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5) = execAsyncLocked { block(p1, p2, p3, p4, p5) }
    suspend fun susp(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5) = execSuspLocked { block(p1, p2, p3, p4, p5) }
}

class ExecOp6<P1, P2, P3, P4, P5, P6>(lock: DbLock, private val block: (P1, P2, P3, P4, P5, P6) -> Unit) : BaseLockedOp(lock) {
    operator fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6) = exec(p1, p2, p3, p4, p5, p6)
    fun exec(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6) = execLocked { block(p1, p2, p3, p4, p5, p6) }
    fun async(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6) = execAsyncLocked { block(p1, p2, p3, p4, p5, p6) }
    suspend fun susp(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6) = execSuspLocked { block(p1, p2, p3, p4, p5, p6) }
}

class ExecOp7<P1, P2, P3, P4, P5, P6, P7>(lock: DbLock, private val block: (P1, P2, P3, P4, P5, P6, P7) -> Unit) : BaseLockedOp(lock) {
    operator fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7) = exec(p1, p2, p3, p4, p5, p6, p7)
    fun exec(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7) = execLocked { block(p1, p2, p3, p4, p5, p6, p7) }
    fun async(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7) = execAsyncLocked { block(p1, p2, p3, p4, p5, p6, p7) }
    suspend fun susp(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7) = execSuspLocked { block(p1, p2, p3, p4, p5, p6, p7) }
}

class ExecOp8<P1, P2, P3, P4, P5, P6, P7, P8>(lock: DbLock, private val block: (P1, P2, P3, P4, P5, P6, P7, P8) -> Unit) : BaseLockedOp(lock) {
    operator fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8) = exec(p1, p2, p3, p4, p5, p6, p7, p8)
    fun exec(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8) = execLocked { block(p1, p2, p3, p4, p5, p6, p7, p8) }
    fun async(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8) = execAsyncLocked { block(p1, p2, p3, p4, p5, p6, p7, p8) }
    suspend fun susp(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8) = execSuspLocked { block(p1, p2, p3, p4, p5, p6, p7, p8) }
}

class ExecOp9<P1, P2, P3, P4, P5, P6, P7, P8, P9>(lock: DbLock, private val block: (P1, P2, P3, P4, P5, P6, P7, P8, P9) -> Unit) : BaseLockedOp(lock) {
    operator fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9) = exec(p1, p2, p3, p4, p5, p6, p7, p8, p9)
    fun exec(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9) = execLocked { block(p1, p2, p3, p4, p5, p6, p7, p8, p9) }
    fun async(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9) = execAsyncLocked { block(p1, p2, p3, p4, p5, p6, p7, p8, p9) }
    suspend fun susp(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9) = execSuspLocked { block(p1, p2, p3, p4, p5, p6, p7, p8, p9) }
}

class ExecOp10<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10>(lock: DbLock, private val block: (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10) -> Unit) : BaseLockedOp(lock) {
    operator fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10) = exec(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10)
    fun exec(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10) = execLocked { block(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10) }
    fun async(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10) = execAsyncLocked { block(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10) }
    suspend fun susp(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10) = execSuspLocked { block(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10) }
}



// -------------------------------- GetOp --------------------------------

class GetOp0<R>(lock: DbLock, private val default: R, private val block: () -> R) : BaseLockedOp(lock) {
    fun get(): R = try { execLocked { block() } } catch (_: Exception) { default }
    suspend fun susp(): R = try { execSuspLocked { block() } } catch (_: Exception) { default }
    operator fun invoke(): R = get()
}

class GetOp1<P1, R>(lock: DbLock, private val default: R, private val block: (P1) -> R) : BaseLockedOp(lock) {
    fun get(p1: P1): R = try { execLocked { block(p1) } } catch (_: Exception) { default }
    suspend fun susp(p1: P1): R = try { execSuspLocked { block(p1) } } catch (_: Exception) { default }
    operator fun invoke(p1: P1): R = get(p1)
}

class GetOp2<P1, P2, R>(lock: DbLock, private val default: R, private val block: (P1, P2) -> R) : BaseLockedOp(lock) {
    fun get(p1: P1, p2: P2): R = try { execLocked { block(p1, p2) } } catch (_: Exception) { default }
    suspend fun susp(p1: P1, p2: P2): R = try { execSuspLocked { block(p1, p2) } } catch (_: Exception) { default }
    operator fun invoke(p1: P1, p2: P2): R = get(p1, p2)
}

class GetOp3<P1, P2, P3, R>(lock: DbLock, private val default: R, private val block: (P1, P2, P3) -> R) : BaseLockedOp(lock) {
    fun get(p1: P1, p2: P2, p3: P3): R = try { execLocked { block(p1, p2, p3) } } catch (_: Exception) { default }
    suspend fun susp(p1: P1, p2: P2, p3: P3): R = try { execSuspLocked { block(p1, p2, p3) } } catch (_: Exception) { default }
    operator fun invoke(p1: P1, p2: P2, p3: P3): R = get(p1, p2, p3)
}

class GetOp4<P1, P2, P3, P4, R>(lock: DbLock, private val default: R, private val block: (P1, P2, P3, P4) -> R) : BaseLockedOp(lock) {
    fun get(p1: P1, p2: P2, p3: P3, p4: P4): R = try { execLocked { block(p1, p2, p3, p4) } } catch (_: Exception) { default }
    suspend fun susp(p1: P1, p2: P2, p3: P3, p4: P4): R = try { execSuspLocked { block(p1, p2, p3, p4) } } catch (_: Exception) { default }
    operator fun invoke(p1: P1, p2: P2, p3: P3, p4: P4): R = get(p1, p2, p3, p4)
}

class GetOp5<P1, P2, P3, P4, P5, R>(lock: DbLock, private val default: R, private val block: (P1, P2, P3, P4, P5) -> R) : BaseLockedOp(lock) {
    fun get(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5): R = try { execLocked { block(p1, p2, p3, p4, p5) } } catch (_: Exception) { default }
    suspend fun susp(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5): R = try { execSuspLocked { block(p1, p2, p3, p4, p5) } } catch (_: Exception) { default }
    operator fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5): R = get(p1, p2, p3, p4, p5)
}

class GetOp6<P1, P2, P3, P4, P5, P6, R>(lock: DbLock, private val default: R, private val block: (P1, P2, P3, P4, P5, P6) -> R) : BaseLockedOp(lock) {
    fun get(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6): R = try { execLocked { block(p1, p2, p3, p4, p5, p6) } } catch (_: Exception) { default }
    suspend fun susp(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6): R = try { execSuspLocked { block(p1, p2, p3, p4, p5, p6) } } catch (_: Exception) { default }
    operator fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6): R = get(p1, p2, p3, p4, p5, p6)
}

class GetOp7<P1, P2, P3, P4, P5, P6, P7, R>(lock: DbLock, private val default: R, private val block: (P1, P2, P3, P4, P5, P6, P7) -> R) : BaseLockedOp(lock) {
    fun get(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7): R = try { execLocked { block(p1, p2, p3, p4, p5, p6, p7) } } catch (_: Exception) { default }
    suspend fun susp(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7): R = try { execSuspLocked { block(p1, p2, p3, p4, p5, p6, p7) } } catch (_: Exception) { default }
    operator fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7): R = get(p1, p2, p3, p4, p5, p6, p7)
}

class GetOp8<P1, P2, P3, P4, P5, P6, P7, P8, R>(lock: DbLock, private val default: R, private val block: (P1, P2, P3, P4, P5, P6, P7, P8) -> R) : BaseLockedOp(lock) {
    fun get(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8): R = try { execLocked { block(p1, p2, p3, p4, p5, p6, p7, p8) } } catch (_: Exception) { default }
    suspend fun susp(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8): R = try { execSuspLocked { block(p1, p2, p3, p4, p5, p6, p7, p8) } } catch (_: Exception) { default }
    operator fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8): R = get(p1, p2, p3, p4, p5, p6, p7, p8)
}

class GetOp9<P1, P2, P3, P4, P5, P6, P7, P8, P9, R>(lock: DbLock, private val default: R, private val block: (P1, P2, P3, P4, P5, P6, P7, P8, P9) -> R) : BaseLockedOp(lock) {
    fun get(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9): R = try { execLocked { block(p1, p2, p3, p4, p5, p6, p7, p8, p9) } } catch (_: Exception) { default }
    suspend fun susp(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9): R = try { execSuspLocked { block(p1, p2, p3, p4, p5, p6, p7, p8, p9) } } catch (_: Exception) { default }
    operator fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9): R = get(p1, p2, p3, p4, p5, p6, p7, p8, p9)
}

class GetOp10<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, R>(lock: DbLock, private val default: R, private val block: (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10) -> R) : BaseLockedOp(lock) {
    fun get(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10): R = try { execLocked { block(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10) } } catch (_: Exception) { default }
    suspend fun susp(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10): R = try { execSuspLocked { block(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10) } } catch (_: Exception) { default }
    operator fun invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10): R = get(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10)
}


