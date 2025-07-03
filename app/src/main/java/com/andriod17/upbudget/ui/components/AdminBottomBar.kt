package com.andriod17.upbudget.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.andriod17.upbudget.ui.navigation.NavItem


@Composable
fun AdminBottomBar(
    selectedItem: String,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val adminNavItems = listOf(
        NavItem("Home", Icons.Default.Home, "admin_home"),
        NavItem("Settings", Icons.Default.Settings, "admin_settings")
    )

    NavigationBar(modifier = modifier,containerColor = Color(0xFFDED8F6)) {
        adminNavItems.forEach { item ->
            NavigationBarItem(
                selected = selectedItem == item.route,
                onClick = { onItemSelected(item.route) },
                icon = {
                    Icon(imageVector = item.icon, contentDescription = item.title)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF180F3E),
                    unselectedIconColor = Color.Gray
                )
            )
        }
    }
}

