package com.vankorno.vankornocompose.navig


sealed interface Screen { val name: String }

abstract class BaseScreen(override val name: String) : Screen

object ScrHome : BaseScreen("Home")
object ScrSett : BaseScreen("Sett")
