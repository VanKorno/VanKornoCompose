package com.vankorno.vankornocompose.ops

import com.vankorno.vankornodb.api.DbLock
import com.vankorno.vankornohelpers.eLog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

open class OpsByRunType(                                                        val lock: DbLock
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
    
}



