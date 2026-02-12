package com.vankorno.vankornocompose.navig

import com.vankorno.vankornodb.api.DbRuntime.lops

object Navig {
    fun goTo(scr: Screen) = lops.exec { NavigHooks.goTo(scr) }
    fun goToAsync(scr: Screen) = lops.async { NavigHooks.goTo(scr) }
    suspend fun goToSusp(scr: Screen) = lops.susp { NavigHooks.goTo(scr) }
    
    fun goBack() = lops.exec { NavigHooks.goBack() }
    fun goBackAsync() = lops.async { NavigHooks.goBack() }
    suspend fun goBackSusp() = lops.susp { NavigHooks.goBack() }
    
    fun goToStart() = lops.exec { NavigHooks.goToStart() }
    fun goToStartAsync() = lops.async { NavigHooks.goToStart() }
    suspend fun goToStartSusp() = lops.susp { NavigHooks.goToStart() }
    
    fun updateScreen() = lops.exec { NavigHooks.updateScreen() }
    fun updateScreenAsync() = lops.async { NavigHooks.updateScreen() }
    suspend fun updateScreenSusp() = lops.susp { NavigHooks.updateScreen() }
}