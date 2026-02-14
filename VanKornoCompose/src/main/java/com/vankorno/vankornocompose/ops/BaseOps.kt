package com.vankorno.vankornocompose.ops

import com.vankorno.vankornocompose.navig.Navig
import com.vankorno.vankornocompose.navig.ScrHome
import com.vankorno.vankornocompose.navig.Screen
import com.vankorno.vankornocompose.values.LibGlobals2.ops
import com.vankorno.vankornodb.api.DbLock
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


sealed class BaseLockedOp(protected val lock: DbLock) {
    protected inline fun <T> execLocked(crossinline block: () -> T): T = lock.withLock { block() }
    
    protected fun execAsyncLocked(block: () -> Unit) {
        CoroutineScope(Dispatchers.Default).launch { execLocked(block) }
    }
    protected suspend inline fun <T> execSuspLocked(crossinline block: () -> T): T =
        withContext(Dispatchers.Default) { execLocked { block() } }
}




object TestOps {
    val goToScrrr = ops.get("") { scr: Screen, str: String ->
        Navig.goTo(scr)
        ""
    }
}

fun myFun() {
    TestOps.goToScrrr(ScrHome, "")
}