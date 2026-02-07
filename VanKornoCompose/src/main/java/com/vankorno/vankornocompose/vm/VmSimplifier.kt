package com.vankorno.vankornocompose.vm

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.MutableStateFlow

class VmVal<T>(                                                        default: T,
                                                             private val onSet: ((T)->Unit)? = null,
) {
    val flow = MutableStateFlow(default)

    var value: T
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
) {
    val flow = MutableStateFlow(ssh[key] ?: default)
    
    var value: T
        get() = flow.value
        set(new) {
            flow.value = new
            ssh[key] = new
            onSet?.invoke(new)
        }
}


@Composable
fun <T> VmVal<T>.state(): State<T> = flow.collectAsStateWithLifecycle()

@Composable
fun <T> VmSavedVal<T>.state(): State<T> = flow.collectAsStateWithLifecycle()