package com.vankorno.vankornocompose.ops

import com.vankorno.vankornodb.api.DbLock

class BundledOpsRunner(                                                         val lock: DbLock
) {
    // ======================= Exec ======================= \\
    fun exec(funName: String = "exec", block: () -> Unit) = ExecOp0(lock, funName, block)
    fun <P1> exec(funName: String = "exec", block: (P1) -> Unit) = ExecOp1(lock, funName, block)
    fun <P1, P2> exec(funName: String = "exec", block: (P1, P2) -> Unit) = ExecOp2(lock, funName, block)
    fun <P1, P2, P3> exec(funName: String = "exec", block: (P1, P2, P3) -> Unit) = ExecOp3(lock, funName, block)
    fun <P1, P2, P3, P4> exec(funName: String = "exec", block: (P1, P2, P3, P4) -> Unit) = ExecOp4(lock, funName, block)
    fun <P1, P2, P3, P4, P5> exec(funName: String = "exec", block: (P1, P2, P3, P4, P5) -> Unit) = ExecOp5(lock, funName, block)
    fun <P1, P2, P3, P4, P5, P6> exec(funName: String = "exec", block: (P1, P2, P3, P4, P5, P6) -> Unit) = ExecOp6(lock, funName, block)
    fun <P1, P2, P3, P4, P5, P6, P7> exec(funName: String = "exec", block: (P1, P2, P3, P4, P5, P6, P7) -> Unit) = ExecOp7(lock, funName, block)
    fun <P1, P2, P3, P4, P5, P6, P7, P8> exec(funName: String = "exec", block: (P1, P2, P3, P4, P5, P6, P7, P8) -> Unit) = ExecOp8(lock, funName, block)
    fun <P1, P2, P3, P4, P5, P6, P7, P8, P9> exec(funName: String = "exec", block: (P1, P2, P3, P4, P5, P6, P7, P8, P9) -> Unit) = ExecOp9(lock, funName, block)
    fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> exec(funName: String = "exec", block: (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10) -> Unit) = ExecOp10(lock, funName, block)
}
