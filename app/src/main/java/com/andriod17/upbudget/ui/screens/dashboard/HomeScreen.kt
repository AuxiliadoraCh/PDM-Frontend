package com.andriod17.upbudget.ui.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.andriod17.upbudget.ui.components.IncomeSummarySection
import com.andriod17.upbudget.ui.components.LimitProgressSection
import com.andriod17.upbudget.viewmodel.Dashboard.HomeScreenViewModel
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.andriod17.upbudget.R
import com.andriod17.upbudget.data.model.Home.HomeUi
import com.andriod17.upbudget.ui.components.CategoriesSection
import com.andriod17.upbudget.ui.components.CustomScaffold
import com.andriod17.upbudget.ui.components.DashboardActionButton

@Composable
fun HomeScreen() {
    val viewModel: HomeScreenViewModel = viewModel()
    val state by viewModel.uiState.collectAsState()

    CustomScaffold(
        title = "Dashboard",
        showBackButton = false,
        useOptionsIcon = true,
        onOptionsClick = { },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {  },
                containerColor = Color(0xFFB9A9EA)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Category",
                    tint = Color.White
                )
            }
        }
    ) { innerPadding ->
        HomeScreenContent(
            state = state,
            modifier = Modifier
                .padding(innerPadding)
                .background(Color.White)
        )
    }
}

@Composable
fun HomeScreenContent(
    state: HomeUi,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        IncomeSummarySection(
            incomeAmount = "$${state.income}",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        LimitProgressSection(
            percentage = (state.progress * 100).toInt(),
            progress = state.progress,
            valueReached = "$${state.spent}",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            DashboardActionButton(
                iconPainter = painterResource(R.drawable.promotions),
                label = "Promotions",
                onClick = { /* TODO */ }
            )
            DashboardActionButton(
                iconVector = Icons.Default.Info,
                label = "Information",
                onClick = { /* TODO */ }
            )
        }
        Spacer(modifier = Modifier.padding(top = 24.dp))
        CategoriesSection()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
    )
}