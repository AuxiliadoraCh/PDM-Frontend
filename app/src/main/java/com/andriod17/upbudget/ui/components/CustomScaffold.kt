package com.andriod17.upbudget.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.andriod17.upbudget.ui.icons.IconContent
import com.andriod17.upbudget.ui.icons.IconHome
import com.andriod17.upbudget.ui.icons.IconPromotions
import com.andriod17.upbudget.ui.icons.IconSettings
import com.andriod17.upbudget.ui.navigation.NavItem
@Composable
fun CustomScaffold(
    title: String = "UPBudget",
    navController: NavHostController,
    content: @Composable (PaddingValues) -> Unit
) {
    var selectedItem by rememberSaveable { mutableStateOf("home") }

    val navItems = listOf(
        NavItem("Home", IconHome, "home"),
        NavItem("Promotions", IconPromotions, "promotions"),
        NavItem("Content", IconContent, "financial_tips"),
        NavItem("Settings", IconSettings, "settings")
    )

    Scaffold(
        modifier = Modifier.background(Color.White), // Asegura que el fondo sea blanco
        containerColor = Color.White,
        topBar = {
            TopBar(
                title = title,
                onBackPressed = {},
                onSettingsPressed = {},
                showBackButton = true,
                showSettingsIcon = true,
                useOptionsIcon = false,
                onOptionsClick = {}
            )
        },
        bottomBar = {
            NavigationBarComponent(
                navItems = navItems,
                selectedItem = selectedItem,
                onItemSelected = { route ->
                    selectedItem = route
                    navController.navigate(route)
                }
            )
        }
    ) { innerPadding ->
        content(innerPadding)
    }
}


@Preview(showBackground = true)
@Composable
fun CustomScaffoldPreview() {
    //CustomScaffold()
}
