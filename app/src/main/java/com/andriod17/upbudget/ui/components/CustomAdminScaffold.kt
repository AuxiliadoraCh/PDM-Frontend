package com.andriod17.upbudget.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.andriod17.upbudget.ui.icons.IconContent
import com.andriod17.upbudget.ui.icons.IconHome
import com.andriod17.upbudget.ui.icons.IconPromotions
import com.andriod17.upbudget.ui.icons.IconSettings
import com.andriod17.upbudget.ui.navigation.*


@Composable
fun CustomAdminScaffold(
    onBackPressed: () -> Unit = {},
    onSettingsPressed: () -> Unit = {},
    showBackButton: Boolean = true,
    showSettingsIcon: Boolean = true,
    useOptionsIcon: Boolean = false,
    onOptionsClick: () -> Unit = {},
    floatingActionButton: @Composable (() -> Unit)? = null,
    content: @Composable (PaddingValues) -> Unit = {},
    navController: NavHostController
) {
    var title by rememberSaveable { mutableStateOf("Inicio") }
    var selectedItem by rememberSaveable { mutableStateOf("admin_home") }


    val navItems = listOf(
        NavItem("Inicio", IconHome, "admin_home"),
        NavItem("Configuración", IconSettings, "admin_settings")
    )

    fun onItemSelected(currentItem: String) {
        selectedItem = currentItem
        title = when (currentItem) {
            "admin_home" -> "Inicio Administrador"
            "admin_settings" -> "Configuración Administrador"
            else -> "UPBudget"
        }

        when (currentItem) {
            "admin_home" -> navController.navigate(AdminHomeNavigation)
            "admin_settings" -> navController.navigate(AdminSettingsNavigation)
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
fun CustomAdminScaffoldPreview() {
    val navController = rememberNavController()
    CustomScaffold(navController = navController)
}
