package com.andriod17.upbudget.ui.screens.admin

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.andriod17.upbudget.data.model.user.UserInfo
import com.andriod17.upbudget.ui.components.CustomAdminScaffold
import com.andriod17.upbudget.ui.navigation.AdminHomeNavigation

@Composable
fun UserDetailScreen(
    user: UserInfo,
    navController: NavHostController,
    onSuspendUser: () -> Unit,
    onResetPassword: () -> Unit
) {
    CustomAdminScaffold(
        navController = navController,
        onBackPressed = {
            navController.navigate(AdminHomeNavigation)
        },
        showBackButton = true,
        showSettingsIcon = false,
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(24.dp))


                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "User Avatar",
                        tint = Color(0xFFB3A9D7),
                        modifier = Modifier.size(120.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))


                Text(
                    text = user.name,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Text(
                    text = "@${user.username}",
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFEDEAFB))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Email: ${user.email}")
                        Text("Registered: ${user.registeredDate}")
                        Text("Total transactions: ${user.totalTransactions}")
                        Text("Last login: ${user.lastLogin}")
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = onSuspendUser,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    border = BorderStroke(1.dp, Color.Red),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Suspend user", color = Color.Red)
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = onResetPassword,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    border = BorderStroke(1.dp, Color.Red),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Reset password", color = Color.Red)
                }
            }
        }
    )
}
