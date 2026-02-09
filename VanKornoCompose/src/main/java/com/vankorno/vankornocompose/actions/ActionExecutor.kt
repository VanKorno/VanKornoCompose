package com.vankorno.vankornocompose.actions

import com.vankorno.vankornodb.api.DbLock

class ActionExecutor(                                                         val dbLock: DbLock
) {
    inline fun <T> action(                                                  defaultValue: T,
                                                                       crossinline block: ()->T,
    ): T {
        return try {
            dbLock.withLock {
                block()
            }
        } catch (e: Exception) {
            defaultValue
        }
    }
    
    inline fun actionVoid(                                             crossinline block: ()->Unit
    ) {
        try {
            dbLock.withLock {
                block()
            }
        } catch (_: Exception) {
        }
    }
}