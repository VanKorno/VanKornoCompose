package com.vankorno.vankornocompose.ops

import com.vankorno.vankornodb.api.DbLock
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OpsRunnerSimple(                                                          val lock: DbLock
) {
    inline fun <T> exec(                                                    defaultValue: T,
                                                                       crossinline block: ()->T,
    ): T {
        return try {
            lock.withLock { block() }
        } catch (_: Exception) {
            defaultValue
        }
    }
    
    inline fun exec(                                                   crossinline block: ()->Unit
    ) {
        exec(Unit) { block() }
    }
    
    fun async(                                                                     block: ()->Unit
    ) {
        CoroutineScope(Dispatchers.Default).launch {
            try {
                lock.withLock { block() }
            } catch (_: Exception) { }
        }
    }
    
    suspend inline fun <T> susp(                                            defaultValue: T,
                                                                       crossinline block: ()->T,
    ): T = withContext(Dispatchers.Default) {
        try {
            lock.withLock { block() }
        } catch (_: Exception) {
            defaultValue
        }
    }
    
    suspend inline fun susp(                                           crossinline block: ()->Unit
    ) {
        susp(Unit) { block() }
    }
}
