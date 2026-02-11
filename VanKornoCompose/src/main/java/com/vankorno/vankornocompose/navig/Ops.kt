package com.vankorno.vankornocompose.navig

import com.vankorno.vankornodb.api.LOps


fun LOps.goTo(scr: Screen) = exec { NavigHooks.goTo(scr) }

fun LOps.goToAsync(scr: Screen) = async { NavigHooks.goTo(scr) }

suspend fun LOps.goToSusp(scr: Screen) = susp { NavigHooks.goTo(scr) }



fun LOps.goBack() = exec { NavigHooks.goBack() }

fun LOps.goBackAsync() = async { NavigHooks.goBack() }

suspend fun LOps.goBackSusp() = susp { NavigHooks.goBack() }



fun LOps.goHome() = exec { NavigHooks.goTo(ScrHome) }

fun LOps.goHomeAsync() = async { NavigHooks.goTo(ScrHome) }

suspend fun LOps.goHomeSusp() = susp { NavigHooks.goTo(ScrHome) }



fun LOps.updateScreen() = exec { NavigHooks.updateScreen() }

fun LOps.updateScreenAsync() = async { NavigHooks.updateScreen() }

suspend fun LOps.updateScreenSusp() = susp { NavigHooks.updateScreen() }


