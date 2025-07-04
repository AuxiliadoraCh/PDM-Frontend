package com.andriod17.upbudget.ui.screens.admin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.andriod17.upbudget.ui.components.CustomAdminScaffold
import com.andriod17.upbudget.ui.navigation.PromotionManagementNavigation
import com.andriod17.upbudget.ui.navigation.UserManagementNavigation

@Composable
fun AdminDashboardScreen(navController: NavHostController) {

    CustomAdminScaffold(
        navController = navController,
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp, vertical = 16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        MetricCard(title = "Active Users", value = "128", modifier = Modifier.weight(1f))
                        MetricCard(title = "Active Coupons", value = "34", modifier = Modifier.weight(1f))
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Button(
                            onClick = { navController.navigate(UserManagementNavigation) },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Users Management")
                        }

                        Button(
                            onClick = { navController.navigate(PromotionManagementNavigation) },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Promotions Management")
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun MetricCard(title: String, value: String, modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF2EEFC)),
        modifier = modifier
            .height(100.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = title, style = MaterialTheme.typography.labelMedium, color = Color.Gray)
            Text(text = value, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun AdminDashboardPreview() {
//    AdminDashboardScreen()
//}
