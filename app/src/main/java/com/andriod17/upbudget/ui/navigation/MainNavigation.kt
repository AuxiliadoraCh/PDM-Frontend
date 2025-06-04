package com.andriod17.upbudget.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.andriod17.upbudget.ui.screens.dashboard.HomeScreen
import com.andriod17.upbudget.ui.screens.info.FinancialTipsScreen
import com.andriod17.upbudget.ui.screens.promotions.PromotionScreen
import com.andriod17.upbudget.ui.screens.settings.SettingsScreen

@Composable
fun MainNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = PromotionsNavigation) {
        composable<HomeNavigation> {
            HomeScreen(onNavigate = { navController.navigate(PromotionsNavigation) })
        }
        composable<PromotionsNavigation> {
            PromotionScreen(onNavigate = { navController.navigate(ContentNavigation) })
        }
        composable<ContentNavigation> {
            FinancialTipsScreen(onNavigate = { navController.navigate(SettingsNavigation) })
        }
        composable<SettingsNavigation> {
            SettingsScreen(onNavigateBack = { navController.popBackStack() })
        }
    }
}
