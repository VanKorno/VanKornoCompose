package com.vankorno.vankornocompose.vm

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

interface VmEventHolder<T> {
    val flow: SharedFlow<T>
    suspend fun emit(value: T)
}


class VmEvent(                                                            onEmit: (()->Unit)? = null
) : VmEventPro<Unit>(
    onEmit = { onEmit?.invoke() }
) {
    suspend fun emit() = super.emit(Unit)
    fun tryEmit() = super.tryEmit(Unit)
    fun fire() = super.fire(Unit)
}


open class VmEventPro<T>(                private val scope: CoroutineScope? = null,
                                                    replay: Int = 0,
                                       extraBufferCapacity: Int = 0,
                                          onBufferOverflow: BufferOverflow = BufferOverflow.SUSPEND,
                                        private val onEmit: ((T)->Unit)? = null,
) : VmEventHolder<T> {
    
    private val _flow = MutableSharedFlow<T>(
        replay = replay,
        extraBufferCapacity = extraBufferCapacity,
        onBufferOverflow = onBufferOverflow
    )
    
    override val flow: SharedFlow<T> = _flow
    
    override suspend fun emit(value: T) {
        onEmit?.invoke(value)
        _flow.emit(value)
    }
    
    fun tryEmit(value: T): Boolean {
        val ok = _flow.tryEmit(value)
        if (ok) onEmit?.invoke(value)
        return ok
    }
    
    fun fire(value: T) {
        scope?.launch { emit(value) } ?: tryEmit(value)
    }
}



@Composable
fun <T> VmEventHolder<T>.collect(                                           block: suspend (T)->Unit
) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    LaunchedEffect(this, lifecycle) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collect { block(it) }
        }
    }
}


@Composable
fun <T> VmEventHolder<T>.collectLatest(                                     block: suspend (T)->Unit
) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    LaunchedEffect(this, lifecycle) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collectLatest { block(it) }
        }
    }
}




