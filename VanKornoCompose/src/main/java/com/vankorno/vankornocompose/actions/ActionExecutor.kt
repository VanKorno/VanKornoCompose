package com.vankorno.vankornocompose.actions

import com.vankorno.vankornodb.api.DbLock
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActionExecutor(                                                         val lock: DbLock
) {

    inline fun <T> run(                                                     defaultValue: T,
                                                                       crossinline block: ()->T,
    ): T {
        return try {
            lock.withLock { block() }
        } catch (e: Exception) {
            defaultValue
        }
    }



    fun voidRun(                                                              async: Boolean = false,
                                                                        block: ()->Unit,
    ) {
        if (async) {
            CoroutineScope(Dispatchers.Default).launch {
                run(Unit) { block() }
            }
        } else {
            run(Unit) { block() }
        }
    }



    suspend fun <T> runSusp(                                                  defaultValue: T,
                                                                        block: ()->T,
    ): T = withContext(Dispatchers.Default) {
        run(defaultValue) { block() }
    }
}
