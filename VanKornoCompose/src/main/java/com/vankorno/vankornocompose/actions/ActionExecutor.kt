package com.vankorno.vankornocompose.actions

import com.vankorno.vankornodb.api.DbLock
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActionExecutor(                                                           val lock: DbLock
) {
    
    inline fun <T> run(                                                     defaultValue: T,
                                                                       crossinline block: ()->T,
    ): T {
        return try {
            lock.withLock {
                block()
            }
        } catch (_: Exception) {
            defaultValue
        }
    }
    
    
    fun launch(                                                                    block: ()->Unit,
    ) {
        CoroutineScope(Dispatchers.Default).launch {
            try {
                lock.withLock {
                    block()
                }
            } catch (_: Exception) {
            }
        }
    }
    
    
    suspend inline fun <T> runSusp(                                         defaultValue: T,
                                                                       crossinline block: ()->T,
    ): T = withContext(Dispatchers.Default) {
        try {
            lock.withLock {
                block()
            }
        } catch (_: Exception) {
            defaultValue
        }
    }
}







