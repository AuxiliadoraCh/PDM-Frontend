package com.andriod17.upbudget.ui.navigation
import kotlinx.serialization.Serializable

sealed interface AppDestination {
    val route: String
}

object HomeNavigation : AppDestination {
    override val route = "home"
}

object PromotionsNavigation : AppDestination {
    override val route = "promotions"
}

object ContentNavigation : AppDestination {
    override val route = "financial_tips"
}

object SettingsNavigation : AppDestination {
    override val route = "settings"
}

