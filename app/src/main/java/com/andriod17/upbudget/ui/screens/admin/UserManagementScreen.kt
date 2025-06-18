package com.andriod17.upbudget.ui.screens.admin

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.andriod17.upbudget.data.model.user.UserInfo
import com.andriod17.upbudget.ui.components.AdminBottomBar
import com.andriod17.upbudget.ui.components.TopBar
import com.andriod17.upbudget.ui.components.UserCard
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment


@Composable
fun UserManagementScreen() {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    var userList by remember {
        mutableStateOf(
            listOf(
                UserInfo("Alexis Merino", "alexis", "alexismerino@uca.edu.sv", "","",4,""),
                UserInfo("Mariana López", "marianal", "mariana@uca.edu.sv", "","",3,""),
                UserInfo("Carlos Ruiz", "cruiz", "cruiz@uca.edu.sv", "","",2,"")
            )
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 64.dp) // espacio para el bottom bar
        ) {
            TopBar(
                title = "User Management",
                onBackPressed = {},
                onSettingsPressed = {}
            )

            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Find users") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null
                    )
                }
            )

            LazyColumn(modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)) {
                items(userList.filter {
                    it.name.contains(searchQuery.text, ignoreCase = true) ||
                            it.email.contains(searchQuery.text, ignoreCase = true)
                }) { user ->
                    UserCard(user = user, onViewClicked = {})
                }
            }
        }

        // Bottom bar fijo al fondo
        AdminBottomBar(
            selectedItem = "admin_settings",
            onItemSelected = { },
            modifier = Modifier
                .align(Alignment.BottomCenter)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UserManagementScreenPreview() {
    UserManagementScreen()
}


