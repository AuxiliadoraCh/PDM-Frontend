package com.andriod17.upbudget.ui.screens.admin

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.andriod17.upbudget.data.model.user.UserInfo
import com.andriod17.upbudget.ui.components.TopBar

@Composable
fun UserDetailScreen(
    user: UserInfo,
    onBackPressed: () -> Unit,
    onSuspendUser: () -> Unit,
    onResetPassword: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TopBar(
            title = "User Information",
            onBackPressed = onBackPressed,
            onSettingsPressed = {}
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Avatar
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "User Avatar",
                tint = Color(0xFFB3A9D7),
                modifier = Modifier.size(120.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Name and username
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

        // Info Card
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

@Preview(showBackground = true)
@Composable
fun UserDetailScreenPreview() {
    UserDetailScreen(
        user = UserInfo(
            name = "Alexis Merino",
            username = "alexis",
            email = "alexis@example.com",
            registeredDate = "April 5, 2024",
            totalTransactions = 110,
            lastLogin = "May 13, 2025",
            imageUrl = "https://www.georgetown.edu/wp-content/uploads/2022/02/Jkramerheadshot-scaled-e1645036825432-1050x1050-c-default.jpg"
        ),
        onBackPressed = {},
        onSuspendUser = {},
        onResetPassword = {}
    )
}

