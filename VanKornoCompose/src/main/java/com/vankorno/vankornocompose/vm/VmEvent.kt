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

/**
 * Base contract for an event holder delivering one-off events of type [T].
 *
 * Designed for ViewModel-to-UI and component communication where events
 * should be handled once and not re-triggered upon recomposition.
 */
interface VmEventHolder<T> {
    /**
     * Read-only [SharedFlow] stream exposed to consumers.
     */
    val flow: SharedFlow<T>
    
    /**
     * Suspends until [value] is emitted to all active collectors or buffer capacity.
     */
    suspend fun emit(value: T)
}


/**
 * Parameterless event emitter (payload is [Unit]) for action/trigger signals,
 * such as button clicks, dialog dismissals, or focus requests.
 *
 * @param onEmit Optional callback executed whenever an event is emitted.
 */
class VmEvent(                                                            onEmit: (()->Unit)? = null
) : VmEventPro<Unit>(
    onEmit = { onEmit?.invoke() }
) {
    /**
     * Suspends and emits the event.
     */
    suspend fun emit() = super.emit(Unit)
    
    /**
     * Attempts to emit the event immediately without suspending.
     * @return `true` if emission succeeded, `false` otherwise.
     */
    fun tryEmit() = super.tryEmit(Unit)
    
    /**
     * Fires the event from synchronous code. Uses [CoroutineScope] if available,
     * otherwise falls back to [tryEmit].
     */
    fun fire() = super.fire(Unit)
}


/**
 * Configurable event emitter for payload-bearing events of type [T].
 *
 * Wraps [MutableSharedFlow] with support for optional [CoroutineScope] launching,
 * buffer tuning, overflow strategies, and emission interceptors.
 *
 * @param T The type of data payload carried by the event.
 * @param scope Optional [CoroutineScope] used by [fire] to launch coroutines asynchronously.
 * @param replay Number of previous events replayed to new subscribers (defaults to 0 for one-off events).
 * @param extraBufferCapacity Extra buffer capacity reserved for emissions beyond [replay].
 * @param onBufferOverflow Policy applied when the buffer is full (defaults to [BufferOverflow.SUSPEND]).
 * @param onEmit Optional side-effect callback invoked on every emitted item.
 */
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
    
    /**
     * Tries to emit [value] without suspending.
     * Invokes [onEmit] only if emission was accepted by the buffer.
     *
     * @return `true` if emitted successfully, `false` otherwise.
     */
    fun tryEmit(value: T): Boolean {
        val ok = _flow.tryEmit(value)
        if (ok) onEmit?.invoke(value)
        return ok
    }
    
    /**
     * Fires [value] from non-suspending contexts. Launches a coroutine in [scope]
     * if configured; otherwise attempts a non-suspending [tryEmit].
     */
    fun fire(value: T) {
        scope?.launch { emit(value) } ?: tryEmit(value)
    }
}


/**
 * Lifecycle-aware Composable collector for [VmEventHolder].
 *
 * Collects events sequentially using [Lifecycle.State.STARTED]. Automatically pauses
 * collection when the lifecycle falls below [Lifecycle.State.STARTED] and resumes when active.
 *
 * @param block Suspending lambda executed for every received event.
 */
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

/**
 * Lifecycle-aware Composable collector that cancels ongoing processing when a new event arrives.
 *
 * Best suited for rapid-fire events such as search query triggers or focus adjustments
 * where only the latest event is relevant.
 *
 * @param block Suspending lambda executed for the latest event.
 */
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




