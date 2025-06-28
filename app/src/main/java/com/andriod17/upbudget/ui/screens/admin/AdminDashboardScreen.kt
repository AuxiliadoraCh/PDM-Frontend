package com.andriod17.upbudget.ui.screens.admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andriod17.upbudget.ui.components.AdminBottomBar
import com.andriod17.upbudget.ui.components.TopBar
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.column.columnChart
import com.patrykandpatrick.vico.core.entry.entryModelOf

@Composable
fun AdminDashboardScreen(
    onNavigateToUsers: () -> Unit,
    onNavigateToPromotions: () -> Unit
) {
    // Datos quemados por ahora (mock)
    val activeUsers = 1280
    val activePromotions = 51
    val usersPerDay = listOf(800f, 850f, 1000f, 950f, 300f, 500f, 400f) // Float desde el inicio
    val chartData = entryModelOf(
        *usersPerDay.mapIndexed { index, value ->
            index.toFloat() to value
        }.toTypedArray()
    )
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            title = "Admin Dashboard",
            onBackPressed = { /* Sin navegación atrás aún */ },
            onSettingsPressed = { /* Acción futura */ }
        )

        Column(
            modifier = Modifier
                .padding(16.dp)
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "$activeUsers", fontSize = 24.sp, color = Color(0xFF180F3E))
                    Text(text = "Active\nusers", textAlign = androidx.compose.ui.text.style.TextAlign.Center)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "$activePromotions", fontSize = 24.sp, color = Color(0xFF180F3E))
                    Text(text = "Active\npromotions", textAlign = androidx.compose.ui.text.style.TextAlign.Center)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(text = "Active users per day", fontSize = 18.sp)
            Chart(
                chart = columnChart(),
                model = chartData
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = onNavigateToUsers) {
                    Text("Users management")
                }
                Button(onClick = onNavigateToPromotions) {
                    Text("Promotions Management")
                }
            }
        }

        AdminBottomBar(
            selectedItem = "admin_home",
            onItemSelected = {  }
        )
    }
}