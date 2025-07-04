import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.andriod17.upbudget.data.model.Expense.ExpenseUi
import com.andriod17.upbudget.data.model.user.UserInfo
import com.andriod17.upbudget.ui.navigation.AdminHomeNavigation
import com.andriod17.upbudget.ui.navigation.AdminSettingsNavigation
import com.andriod17.upbudget.ui.navigation.EditProfileNavigation
import com.andriod17.upbudget.ui.navigation.ExpenseHistoryNavigation
import com.andriod17.upbudget.ui.navigation.HomeNavigation
import com.andriod17.upbudget.ui.navigation.LearningNavigation
import com.andriod17.upbudget.ui.navigation.LoginNavigation
import com.andriod17.upbudget.ui.navigation.OnboardingInfoNavigation
import com.andriod17.upbudget.ui.navigation.PromotionManagementNavigation
import com.andriod17.upbudget.ui.navigation.PromotionsNavigation
import com.andriod17.upbudget.ui.navigation.RegisterNavigation
import com.andriod17.upbudget.ui.navigation.SettingsNavigation
import com.andriod17.upbudget.ui.navigation.UsedCouponsNavigation
import com.andriod17.upbudget.ui.navigation.UserInformationNavigation
import com.andriod17.upbudget.ui.navigation.UserManagementNavigation
import com.andriod17.upbudget.ui.screens.Learning.LearningScreen
import com.andriod17.upbudget.ui.screens.admin.AdminDashboardScreen
import com.andriod17.upbudget.ui.screens.admin.PromotionManagementScreen
import com.andriod17.upbudget.ui.screens.admin.UserDetailScreen
import com.andriod17.upbudget.ui.screens.admin.UserManagementScreen
import com.andriod17.upbudget.ui.screens.auth.LoginScreen
import com.andriod17.upbudget.ui.screens.auth.RegisterScreen
import com.andriod17.upbudget.ui.screens.settings.SettingsScreen
import com.andriod17.upbudget.ui.screens.dashboard.HomeScreen
import com.andriod17.upbudget.ui.screens.expenses.ExpenseHistoryScreen
import com.andriod17.upbudget.ui.screens.onboarding.OnboardingInfoScreen
import com.andriod17.upbudget.ui.screens.promotions.PromotionsScreen
import com.andriod17.upbudget.ui.screens.promotions.UsedCouponsScreen
import com.andriod17.upbudget.ui.screens.settings.EditProfileScreen
import com.andriod17.upbudget.viewmodel.Expense.ExpenseScreenViewModel

@Composable
fun MainNavigation(navController: NavHostController, startDestination: Any = OnboardingInfoNavigation) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<ExpenseHistoryNavigation>{
            ExpenseHistoryScreen(navController = navController)
        }
        composable<HomeNavigation>{
            HomeScreen(navController = navController)
        }
        composable<PromotionsNavigation>{
            PromotionsScreen(navController = navController)
        }
        composable<LearningNavigation>{
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
        composable<UserManagementNavigation>{
            UserManagementScreen(navController = navController)
        }
        composable<AdminHomeNavigation>{
            AdminDashboardScreen(navController = navController)
        }
        composable<AdminSettingsNavigation>{
            UserManagementScreen(navController = navController)
        }
        composable<PromotionManagementNavigation>{
            PromotionManagementScreen(navHostController = navController)
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
        composable<UsedCouponsNavigation>{
            UsedCouponsScreen(navController = navController)
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
    }
}
