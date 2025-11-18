package com.vankorno.vankornocompose.navig

sealed interface PopState { val name: String }

abstract class BasePopState(override val name: String) : PopState

object PopStateOFF : BasePopState("OFF")
object PopStateContextInfo : BasePopState("ContextInfo")


