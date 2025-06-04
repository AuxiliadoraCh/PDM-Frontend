package com.andriod17.upbudget.ui.screens.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen(onNavigate: () -> Unit) {
    Column {
        Text("Pantalla Home")
        Button(onClick = onNavigate) {
            Text("Ir a promociones")
        }
    }
}
