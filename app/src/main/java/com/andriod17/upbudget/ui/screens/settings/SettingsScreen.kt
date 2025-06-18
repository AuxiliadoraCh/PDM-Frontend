package com.andriod17.upbudget.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.andriod17.upbudget.data.model.settings.SettingsOption
import com.andriod17.upbudget.data.model.user.UserInfo
import com.andriod17.upbudget.ui.components.CustomScaffold
import com.andriod17.upbudget.ui.components.SettingsSection
import com.andriod17.upbudget.ui.components.UserProfileSection

@Composable
fun SettingsScreen() {
    val accountOptions = listOf(
        SettingsOption(Icons.Filled.Person, "Edit profile") {},
        SettingsOption(Icons.Filled.Lock, "Security") {},
        SettingsOption(Icons.Filled.Notifications, "Notifications") {},
    )

    val supportOptions = listOf(
        SettingsOption(Icons.Filled.Star, "My Subscription") {},
        SettingsOption(Icons.Filled.Face, "Help & Support") {},
        SettingsOption(Icons.Filled.CheckCircle, "Terms and Policies") {}
    )

    val actionOptions = listOf(
        SettingsOption(Icons.Filled.Warning, "Report a problem") {},
        SettingsOption(Icons.AutoMirrored.Filled.ExitToApp, "Log out") {}
    )

    CustomScaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            UserProfileSection(
                user = UserInfo(
                    name = "Ziad Hamdy M",
                    username = "rocio",
                    email = "ziad.hamdy99@gmail.com",
                    imageUrl = "https://www.georgetown.edu/wp-content/uploads/2022/02/Jkramerheadshot-scaled-e1645036825432-1050x1050-c-default.jpg",
                    registeredDate = "",
                    totalTransactions = 3,
                    lastLogin = ""
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            SettingsSection(title = "Account", items = accountOptions)
            SettingsSection(title = "Support & About", items = supportOptions)
            SettingsSection(title = "Actions", items = actionOptions)

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreensPreview() {
    SettingsScreen()
}
