package com.vankorno.vankornocompose.navig

open class BaseNavig {
    open fun goTo(target: Screen) {}
    open fun goBack() {}
    open fun goHome() {}
    open fun updateScreen() {}
}
