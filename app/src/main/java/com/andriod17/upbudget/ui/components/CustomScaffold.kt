package com.andriod17.upbudget.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomScaffold(
) {
    Scaffold(
        topBar = {
            TopAppBar(
            title = { Text(text = "My App") },
            actions = { /* Add actions here if needed */ }
        ) },
        bottomBar = {
            BottomAppBar (
                content = { Text(text = "Bottom Bar") }
            )
            },
        content = { innerPadding ->
            // Content of the screen goes here
            // Use innerPadding to apply padding to your content
            Text(text = "Content goes here", modifier = Modifier.padding(innerPadding))
        }
    )
}
