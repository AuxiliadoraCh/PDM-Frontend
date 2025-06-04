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
import com.andriod17.upbudget.ui.icons.IconReports
import com.andriod17.upbudget.ui.icons.IconSettings
import com.andriod17.upbudget.ui.navigation.AppDestination
import com.andriod17.upbudget.ui.navigation.ContentNavigation
import com.andriod17.upbudget.ui.navigation.HomeNavigation
import com.andriod17.upbudget.ui.navigation.PromotionsNavigation
import com.andriod17.upbudget.ui.navigation.SettingsNavigation

@Composable
fun CustomScaffold(
) {
    var selectedItem by rememberSaveable { mutableStateOf<AppDestination>(PromotionsNavigation) }

    val navItems = listOf(
        NavItem("Home", IconHome, HomeNavigation),
        NavItem("Promotions", IconPromotions, PromotionsNavigation),
        NavItem("Content", IconContent, ContentNavigation),
        NavItem("Settings", IconSettings, SettingsNavigation)
    )
    val navController = rememberNavController()

    fun onItemSelected(currentItem: AppDestination) {
        selectedItem = currentItem
        navController.navigate(currentItem)
    }


    Scaffold(
        topBar = {
            TopBar(
                title = when (selectedItem) {
                    HomeNavigation -> "Home"
                    PromotionsNavigation -> "Promotions"
                    ContentNavigation -> "Content"
                    SettingsNavigation -> "Settings"
                },
                onBackPressed = {},
                onSettingsPressed = {},
            )
         },
        bottomBar = {
            NavigationBar(
                navItems = navItems,
                selectedItem = selectedItem,
                onItemSelected = ::onItemSelected
            )
        },
        content = { innerPadding ->
            // Main content of the screen
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
