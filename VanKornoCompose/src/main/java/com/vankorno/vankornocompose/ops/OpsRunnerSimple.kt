package com.vankorno.vankornocompose.ops

import com.vankorno.vankornodb.api.DbLock
import com.vankorno.vankornohelpers.eLog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@PublishedApi
internal const val OpsSimpleTAG = "OpsRunnerSimple"

class OpsRunnerSimple(                                                          val lock: DbLock
) {
    inline fun <T> get(                                               defaultValue: T,
                                                                           funName: String = "get",
                                                                 crossinline block: ()->T,
    ): T {
        return try {
            lock.withLock { block() }
        } catch (e: Exception) {
            // region LOG
                eLog(OpsSimpleTAG, "$funName() failed. Details: ${e.message}", e)
            // endregion
            defaultValue
        }
    }
    
    inline fun exec(                                                      funName: String = "exec",
                                                                crossinline block: ()->Unit,
    ) {
        get(Unit, funName) { block() }
    }
    
    
    fun async(                                                            funName: String = "async",
                                                                            block: ()->Unit,
    ) {
        CoroutineScope(Dispatchers.Default).launch {
            try {
                lock.withLock { block() }
            } catch (e: Exception) {
                // region LOG
                    eLog(OpsSimpleTAG, "$funName() failed. Details: ${e.message}", e)
                // endregion
            }
        }
    }
    
    
    suspend inline fun <T> getSusp(                                defaultValue: T,
                                                                        funName: String = "getSusp",
                                                              crossinline block: ()->T,
    ): T = withContext(Dispatchers.Default) {
        try {
            lock.withLock { block() }
        } catch (e: Exception) {
            // region LOG
                eLog(OpsSimpleTAG, "$funName() failed. Details: ${e.message}", e)
            // endregion
            defaultValue
        }
    }
    
    suspend inline fun susp(                                              funName: String = "susp",
                                                                crossinline block: ()->Unit,
    ) {
        getSusp(Unit, funName) { block() }
    }
}
