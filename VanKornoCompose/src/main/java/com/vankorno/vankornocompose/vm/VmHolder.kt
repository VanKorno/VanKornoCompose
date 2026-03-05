package com.vankorno.vankornocompose.vm

import androidx.activity.ComponentActivity
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class VmHolder {
    @PublishedApi
    internal lateinit var provider: ViewModelProvider
    
    protected inline fun <reified T : ViewModel> get(): T = provider[T::class.java]
    
    fun init(                                                            activity: ComponentActivity
    ) {
        provider = ViewModelProvider(
            owner = activity,
            factory = SavedStateViewModelFactory(activity.application, activity)
        )
        onInit()
    }
    
    protected open fun onInit() {}
}