package com.vankorno.vankornocompose.navig

import com.vankorno.vankornodb.api.LOps


fun LOps.goTo(scr: Screen) = exec { NavigHooks.goTo(scr) }

fun LOps.goToAsync(scr: Screen) = async { NavigHooks.goTo(scr) }

suspend fun LOps.goToSusp(scr: Screen) = susp { NavigHooks.goTo(scr) }



fun LOps.goBack() = exec { NavigHooks.goBack() }

fun LOps.goBackAsync() = async { NavigHooks.goBack() }

suspend fun LOps.goBackSusp() = susp { NavigHooks.goBack() }



fun LOps.goRoot() = exec { NavigHooks.goRoot() }

fun LOps.goRootAsync() = async { NavigHooks.goRoot() }

suspend fun LOps.goRootSusp() = susp { NavigHooks.goRoot() }



fun LOps.updateScreen() = exec { NavigHooks.updateScreen() }

fun LOps.updateScreenAsync() = async { NavigHooks.updateScreen() }

suspend fun LOps.updateScreenSusp() = susp { NavigHooks.updateScreen() }


