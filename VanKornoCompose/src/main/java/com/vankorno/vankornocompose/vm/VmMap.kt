package com.vankorno.vankornocompose.vm

import androidx.lifecycle.SavedStateHandle


class VmMap<K, V>(
    default: Map<K, V> = emptyMap(),
    onSet: ((Map<K, V>) -> Unit)? = null,
) : VmValueHolder<Map<K, V>> by VmVal(default, onSet)

class VmSavedMap<K, V>(
    val ssh: SavedStateHandle,
    val key: String,
    default: Map<K, V> = emptyMap(),
    onSet: ((Map<K, V>) -> Unit)? = null,
) : VmValueHolder<Map<K, V>> by VmSavedVal(ssh, key, default, onSet)





fun <K, V> VmValueHolder<Map<K, V>>.put(key: K, value: V) {
    this.value = this.value + (key to value)
}

fun <K, V> VmValueHolder<Map<K, V>>.putAll(map: Map<K, V>) {
    this.value = this.value + map
}

fun <K, V> VmValueHolder<Map<K, V>>.putAll(vararg pairs: Pair<K, V>) {
    this.value = this.value + pairs
}

@JvmName("removeKey")
fun <K, V> VmValueHolder<Map<K, V>>.remove(key: K) {
    this.value = this.value - key
}

fun <K, V> VmValueHolder<Map<K, V>>.remove(keys: Iterable<K>) {
    this.value = this.value.toMutableMap().apply { keys.forEach { remove(it) } }
}

fun <K, V> VmValueHolder<Map<K, V>>.remove(vararg keys: K) {
    this.value = this.value.toMutableMap().apply { keys.forEach { remove(it) } }
}

@JvmName("clearMap")
fun <K, V> VmValueHolder<Map<K, V>>.clear() {
    this.value = emptyMap()
}

operator fun <K, V> VmValueHolder<Map<K, V>>.get(key: K): V? = value[key]

fun <K, V> VmValueHolder<Map<K, V>>.getOrPut(key: K, defaultValue: () -> V): V {
    val current = value
    return if (current.containsKey(key)) {
        current[key]!!
    } else {
        val computed = defaultValue()
        put(key, computed)
        computed
    }
}

inline fun <K, V> VmValueHolder<Map<K, V>>.updateKey(key: K, block: (V?) -> V?) {
    val current = value
    val updatedValue = block(current[key])
    val newMap = current.toMutableMap()
    if (updatedValue == null) {
        newMap.remove(key)
    } else {
        newMap[key] = updatedValue
    }
    value = newMap
}

