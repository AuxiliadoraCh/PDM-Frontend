package com.andriod17.upbudget.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
