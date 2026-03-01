package com.vankorno.appforcomposelib._vm

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

object vm {
    private lateinit var provider: ViewModelProvider
    
    lateinit var a: VmMain
    
    private inline fun <reified T : ViewModel> get(): T = provider[T::class.java]
    
    
    fun init(                                                           activity: ComponentActivity,
                                                                     application: Application,
    ) {
        provider = ViewModelProvider(
            owner = activity,
            factory = SavedStateViewModelFactory(application, activity)
        )
        
        a = get()
    }
    
    
}