package com.andriod17.upbudget.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.andriod17.upbudget.ui.navigation.NavItem


@Composable
fun NavigationBarComponent(
    navItems: List<NavItem>,
    selectedItem: String,
    onItemSelected: (String) -> Unit
) {
    NavigationBar(containerColor = Color.White) {
        navItems.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                selected = selectedItem == item.route,
                onClick = { onItemSelected(item.route) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF261863),
                    unselectedIconColor = Color(0xFF180F3E),
                    indicatorColor = Color(0xFFCDE5EF)
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NavigationBarPreview(){
    //NavigationBar()
}
