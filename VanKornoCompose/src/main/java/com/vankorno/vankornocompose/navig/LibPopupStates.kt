package com.vankorno.vankornocompose.navig

sealed interface PopupState { val name: String }

abstract class BasePopupState(override val name: String) : PopupState

object PopupOFF : BasePopupState("OFF")
object PopupContextInfo : BasePopupState("ContextInfo")


