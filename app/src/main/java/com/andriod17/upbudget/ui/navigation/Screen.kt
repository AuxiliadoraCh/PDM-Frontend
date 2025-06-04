package com.andriod17.upbudget.ui.navigation
import kotlinx.serialization.Serializable

@Serializable
sealed interface AppDestination

@Serializable
object HomeNavigation : AppDestination

@Serializable
object PromotionsNavigation : AppDestination

@Serializable
object ContentNavigation : AppDestination

@Serializable
object SettingsNavigation : AppDestination
