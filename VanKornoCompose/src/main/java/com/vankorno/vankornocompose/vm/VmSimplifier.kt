package com.vankorno.vankornocompose.vm

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface VmValueHolder<T> {
    var value: T
    val flow: StateFlow<T>
}


class VmVal<T>(                                                        default: T,
                                                             private val onSet: ((T)->Unit)? = null,
) : VmValueHolder<T> {

    override val flow = MutableStateFlow(default)

    override var value: T
        get() = flow.value
        set(new) {
            flow.value = new
            onSet?.invoke(new)
        }
}



class VmSavedVal<T>(                                                   val ssh: SavedStateHandle,
                                                                       val key: String,
                                                                       default: T,
                                                             private val onSet: ((T)->Unit)? = null,
) : VmValueHolder<T> {

    override val flow = MutableStateFlow(ssh[key] ?: default)

    override var value: T
        get() = flow.value
        set(new) {
            flow.value = new
            ssh[key] = new
            onSet?.invoke(new)
        }
}



@Composable fun <T> VmValueHolder<T>.state(): State<T> = flow.collectAsStateWithLifecycle()

inline fun <T> VmValueHolder<T>.update(block: (T) -> T) { value = block(value) }

fun VmValueHolder<Boolean>.flip() { value = !value }

fun <T> VmValueHolder<T?>.clear() { value = null }

fun VmValueHolder<Int>.inc() { value++ }
fun VmValueHolder<Int>.dec() { value-- }

fun <T> VmValueHolder<List<T>>.add(item: T) { value = value + item }
fun <T> VmValueHolder<List<T>>.remove(item: T) { value = value - item }






