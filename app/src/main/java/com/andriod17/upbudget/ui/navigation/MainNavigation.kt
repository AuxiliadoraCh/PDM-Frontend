import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.andriod17.upbudget.ui.screens.Learning.LearningScreen
import com.andriod17.upbudget.ui.screens.settings.SettingsScreen
import com.andriod17.upbudget.ui.screens.dashboard.HomeScreen
import com.andriod17.upbudget.ui.screens.promotions.PromotionsScreen

@Composable
fun MainNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home",
    ) {
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("promotions"){
            PromotionsScreen(navController = navController)
        }
        composable("financial_tips"){
            LearningScreen(navController = navController)
        }
        composable("settings"){
            SettingsScreen(navController = navController)
        }
    }
}