package com.andriod17.upbudget.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.andriod17.upbudget.data.model.user.UserInfo
import com.andriod17.upbudget.ui.screens.Learning.LearningScreen
import com.andriod17.upbudget.ui.screens.admin.PromotionManagementScreen
import com.andriod17.upbudget.ui.screens.admin.UserDetailScreen
import com.andriod17.upbudget.ui.screens.admin.UserManagementScreen
import com.andriod17.upbudget.ui.screens.auth.LoginScreen
import com.andriod17.upbudget.ui.screens.auth.RegisterScreen
import com.andriod17.upbudget.ui.screens.settings.SettingsScreen
import com.andriod17.upbudget.ui.screens.dashboard.HomeScreen
import com.andriod17.upbudget.ui.screens.onboarding.OnboardingInfoScreen
import com.andriod17.upbudget.ui.screens.promotions.PromotionsScreen
import com.andriod17.upbudget.ui.screens.promotions.UsedCouponsScreen
import com.andriod17.upbudget.ui.screens.settings.EditProfileScreen

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
        composable<EditProfileNavigation>{
            EditProfileScreen(
                user = UserInfo(
                    name = "Nombre ejemplo",
                    username = "usuario",
                    email = "email@ejemplo.com",
                    imageUrl = "",
                    registeredDate = "",
                    totalTransactions = 0,
                    lastLogin = ""
                ),
                onSave = {},
                navController = navController
            )
        }
        composable<UsedCouponsNavigation>{
            UsedCouponsScreen(navController = navController)
        }
        composable<UserInformationNavigation> {
            UserDetailScreen(
                user = UserInfo(
                    name = "Alexis Merino",
                    username = "alexis",
                    email = "alexis@example.com",
                    registeredDate = "April 5, 2024",
                    totalTransactions = 110,
                    lastLogin = "May 13, 2025",
                    imageUrl = "https://www.georgetown.edu/wp-content/uploads/2022/02/Jkramerheadshot-scaled-e1645036825432-1050x1050-c-default.jpg"
                ),
                onSuspendUser = {},
                onResetPassword = {},
                navController = navController
            )
        }
        composable<UserManagementNavigation>{
            UserManagementScreen(navController = navController)
        }
        composable<AdminHomeNavigation>{
            //AdminDashboardScreen(navController = navController)
        }
        composable<AdminSettingsNavigation>{
            UserManagementScreen(navController = navController)
        }
        composable<PromotionManagementNavigation>{
            PromotionManagementScreen(navHostController = navController)
        }
    }
}