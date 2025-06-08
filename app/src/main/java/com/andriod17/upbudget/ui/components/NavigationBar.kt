package com.andriod17.upbudget.ui.components


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

data class NavItem(
    val label: String,
    val icon: ImageVector,
    val destination: String
)

@Composable
fun NavigationBar(
    navItems: List<NavItem>,
    selectedItem: String,
    onItemSelected: (String) -> Unit
)
{
    NavigationBar(containerColor = Color.White,
    ) {
        navItems.forEach { item ->
            NavigationBarItem(
                modifier = Modifier.align(Alignment.CenterVertically)
                    .padding(horizontal = 11.dp),
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        modifier = Modifier.padding(horizontal = 5.dp, vertical = 10.dp)
                    )
                },
                selected = selectedItem == item.destination,
                onClick = { onItemSelected(item.destination) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(
                        0xFF261863),
                    unselectedIconColor = Color(0xFF180F3E),
                    indicatorColor = Color(0xFFCDE5EF)
                )
            )
        }
    }
}
