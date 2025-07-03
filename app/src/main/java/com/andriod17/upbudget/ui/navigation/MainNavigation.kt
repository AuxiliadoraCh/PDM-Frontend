package com.andriod17.upbudget.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.andriod17.upbudget.ui.screens.Learning.LearningScreen
import com.andriod17.upbudget.ui.screens.auth.LoginScreen
import com.andriod17.upbudget.ui.screens.auth.RegisterScreen
import com.andriod17.upbudget.ui.screens.settings.SettingsScreen
import com.andriod17.upbudget.ui.screens.dashboard.HomeScreen
import com.andriod17.upbudget.ui.screens.onboarding.OnboardingInfoScreen
import com.andriod17.upbudget.ui.screens.promotions.PromotionsScreen

@Composable
fun MainNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = OnboardingInfoNavigation,
    ) {
        composable<HomeNavigation>{
            HomeScreen(navController = navController)
        }
        composable<PromotionsNavigation>{
            PromotionsScreen(navController = navController)
        }
        composable<ContentNavigation>{
            LearningScreen(navController = navController)
        }
        composable<SettingsNavigation>{
            SettingsScreen(navController = navController)
        }
        composable<OnboardingInfoNavigation> {
            OnboardingInfoScreen(navController = navController)
        }
        composable<RegisterNavigation>{
            RegisterScreen(navController = navController)
        }
        composable<LoginNavigation> {
            LoginScreen(navController = navController)
        }
    }
}