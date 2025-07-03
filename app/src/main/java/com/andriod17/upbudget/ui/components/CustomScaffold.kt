package com.andriod17.upbudget.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.andriod17.upbudget.ui.icons.IconContent
import com.andriod17.upbudget.ui.icons.IconHome
import com.andriod17.upbudget.ui.icons.IconPromotions
import com.andriod17.upbudget.ui.icons.IconSettings
import com.andriod17.upbudget.ui.navigation.ContentNavigation
import com.andriod17.upbudget.ui.navigation.HomeNavigation
import com.andriod17.upbudget.ui.navigation.NavItem
import com.andriod17.upbudget.ui.navigation.PromotionsNavigation
import com.andriod17.upbudget.ui.navigation.SettingsNavigation

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
    content: @Composable (PaddingValues) -> Unit = {},
    navController: NavHostController
) {
    var selectedItem by rememberSaveable { mutableStateOf("home") }

    val navItems = listOf(
        NavItem("Home", IconHome, HomeNavigation.toString()),
        NavItem("Promotions", IconPromotions, PromotionsNavigation.toString()),
        NavItem("Content", IconContent, ContentNavigation.toString()),
        NavItem("Settings", IconSettings, SettingsNavigation.toString())
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
            NavigationBarComponent(
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
    //CustomScaffold()
}
