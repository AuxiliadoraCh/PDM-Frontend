package com.andriod17.upbudget.ui.screens.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.navigation.NavHostController
import com.andriod17.upbudget.R
import com.andriod17.upbudget.data.model.Home.HomeUi
import com.andriod17.upbudget.ui.components.CategoriesSection
import com.andriod17.upbudget.ui.components.CustomScaffold
import com.andriod17.upbudget.ui.components.DashboardActionButton
import com.andriod17.upbudget.ui.navigation.ExpenseHistoryNavigation
import com.andriod17.upbudget.ui.navigation.NewExpenseNavigation

@Composable
fun HomeScreenContent(
    state: HomeUi,
    modifier: Modifier = Modifier,
    navController: NavHostController,

    ) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
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
            modifier = Modifier.fillMaxWidth().padding(vertical = 24.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            DashboardActionButton(
                iconPainter = painterResource(R.drawable.promotions),
                label = "Promotions",
                onClick = {
                    //navController.navigate(PromotionsNavigation)
                }
            )
            DashboardActionButton(
                iconVector = Icons.Default.Info,
                label = "Information",
                onClick = {
                    navController.navigate(ExpenseHistoryNavigation)
                }
            )
        }
        CategoriesSection()
    }
}

@Composable
fun HomeScreen(
    navController: NavHostController
) {
    val viewModel: HomeScreenViewModel = viewModel()
    val state by viewModel.uiState.collectAsState()

    CustomScaffold(
        useOptionsIcon = true,
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(NewExpenseNavigation) }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },
        content = { innerPadding ->
            HomeScreenContent(
                state = state,
                modifier = Modifier.padding(innerPadding),
                navController = navController
            )
        },
        navController = navController
    )
}






@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
HomeScreen(
    navController = NavHostController(context = androidx.compose.ui.platform.LocalContext.current)
)}