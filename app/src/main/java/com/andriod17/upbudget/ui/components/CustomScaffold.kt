package com.andriod17.upbudget.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.andriod17.upbudget.ui.icons.IconContent
import com.andriod17.upbudget.ui.icons.IconHome
import com.andriod17.upbudget.ui.icons.IconPromotions
import com.andriod17.upbudget.ui.icons.IconSettings
import com.andriod17.upbudget.ui.navigation.*


@Composable
fun CustomScaffold(
    navController: NavHostController,
    onBackPressed: () -> Unit = {},
    onSettingsPressed: () -> Unit = {},
    showBackButton: Boolean = true,
    showSettingsIcon: Boolean = true,
    useOptionsIcon: Boolean = false,
    onOptionsClick: () -> Unit = {},
    floatingActionButton: @Composable (() -> Unit)? = null,
    content: @Composable (PaddingValues) -> Unit = {},
    title: String = "UPBudget",

) {
    var selectedItem by rememberSaveable { mutableStateOf("home") }


    val navItems = listOf(
        NavItem("Inicio", IconHome, "home"),
        NavItem("Promociones", IconPromotions, "promotions"),
        NavItem("Consejos", IconContent, "financial_tips"),
        NavItem("Configuración", IconSettings, "settings")
    )

    fun onItemSelected(currentItem: String) {
        selectedItem = currentItem
        when (currentItem) {
            "promotions" -> navController.navigate(PromotionsNavigation)
            "settings" -> navController.navigate(SettingsNavigation)
            "financial_tips" -> navController.navigate(LearningNavigation)
            "home" -> navController.navigate(HomeNavigation)
            else -> navController.navigate(currentItem)
        }
    }

    Scaffold(
        topBar = {
            TopBar(
                title = title,
                onBackPressed = onBackPressed,
                onSettingsPressed = onSettingsPressed,
                showBackButton = showBackButton,
                showSettingsIcon = showSettingsIcon,
                useOptionsIcon = useOptionsIcon,
                onOptionsClick = onOptionsClick
            )
        },
        bottomBar = {
            NavigationBarComponent(
                navItems = navItems,
                selectedItem = selectedItem,
                onItemSelected = { onItemSelected(it) }
            )
        },
        floatingActionButton = floatingActionButton ?: {}
    ) { innerPadding ->
        content(innerPadding)
    }
}

@Preview(showBackground = true)
@Composable
fun CustomScaffoldPreview() {
    val navController = rememberNavController()
    CustomScaffold(navController = navController, title = "Vista de ejemplo")
}
