package com.andriod17.upbudget.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.andriod17.upbudget.ui.icons.IconContent
import com.andriod17.upbudget.ui.icons.IconHome
import com.andriod17.upbudget.ui.icons.IconPromotions
import com.andriod17.upbudget.ui.icons.IconSettings
import com.andriod17.upbudget.ui.navigation.AppDestination
import com.andriod17.upbudget.ui.navigation.ContentNavigation
import com.andriod17.upbudget.ui.navigation.HomeNavigation
import com.andriod17.upbudget.ui.navigation.PromotionsNavigation
import com.andriod17.upbudget.ui.navigation.SettingsNavigation

@Composable
fun CustomScaffold() {
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
                title = when (selectedItem) {
                    "home" -> "Home"
                    "promotions" -> "Promotions"
                    "financial_tips" -> "Content"
                    "settings" -> "Settings"
                    else -> "UPBudget"
                },
                onBackPressed = {},
                onSettingsPressed = {}
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
        content = { innerPadding ->
            Text(
                text = "Welcome to UPBudget",
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun CustomScaffoldPreview() {
    CustomScaffold()
}
