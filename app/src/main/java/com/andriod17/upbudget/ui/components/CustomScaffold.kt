package com.andriod17.upbudget.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.andriod17.upbudget.ui.icons.IconContent
import com.andriod17.upbudget.ui.icons.IconHome
import com.andriod17.upbudget.ui.icons.IconPromotions
import com.andriod17.upbudget.ui.icons.IconSettings

@Composable
fun CustomScaffold(
    title: String = "UPBudget",
    onBackPressed: () -> Unit = {},
    onSettingsPressed: () -> Unit = {},
    showBackButton: Boolean = true,
    showSettingsIcon: Boolean = true,
    useOptionsIcon: Boolean = false,
    onOptionsClick: () -> Unit = {},
    floatingActionButton: @Composable (() -> Unit)? = null,
    content: @Composable (PaddingValues) -> Unit

) {
    var selectedItem by rememberSaveable { mutableStateOf("home") }
    val navController = rememberNavController()

    val navItems = listOf(
        NavItem("Home", IconHome, "home"),
        NavItem("Promotions", IconPromotions, "promotions"),
        NavItem("Content", IconContent, "financial_tips"),
        NavItem("Settings", IconSettings, "settings")
    )

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
            NavigationBar(
                navItems = navItems,
                selectedItem = selectedItem,
                onItemSelected = { route ->
                    selectedItem = route
                    navController.navigate(route)
                }
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
    CustomScaffold {
        // Puedes poner un Text() o algo visual de prueba
    }
}
