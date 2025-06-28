package com.andriod17.upbudget.ui.screens.expenses

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.andriod17.upbudget.data.model.Expense.ExpenseUi
import com.andriod17.upbudget.ui.components.BalanceSummarySection
import com.andriod17.upbudget.ui.components.CustomScaffold
import com.andriod17.upbudget.ui.components.ExpenseHistoryItem
import com.andriod17.upbudget.viewmodel.Expense.ExpenseScreenViewModel

@Composable
fun ExpenseHistoryScreen(
    navConsistentCopyVisibility: ConsistentCopyVisibility
) {
    val viewModel: ExpenseScreenViewModel = viewModel()
    // Asegúrate de que 'expenses' sea una lista válida con los datos de gastos e ingresos
    val expenses by viewModel.expenses.collectAsState()

    val balance = viewModel.balance
    val income = viewModel.incomeTotal
    val expense = viewModel.expenseTotal

    CustomScaffold(title = "Expense History",
        content = { ExpenseHistoryScreenContent(
            balance = balance,
            income = income,
            expense = expense,
            expenses = expenses
        ) }, navController =
    )
}

@Composable
fun ExpenseHistoryScreenContent(
    balance: Double,
    income: Double,
    expense: Double,
    expenses: List<ExpenseUi>,
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        BalanceSummarySection(
            balanceAmount = "$${"%.2f".format(balance)}",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Box(
            modifier = Modifier
            .shadow(elevation = 10.dp, spotColor = Color(0x40000000), ambientColor = Color(0x40000000))
                .fillMaxWidth()
                .height(100.dp),
            contentAlignment = Alignment.Center

        ) {
            Text("Income: $${"%.2f".format(income)}", color = Color.Green)
            Text("Expense: $${"%.2f".format(expense)}", color = Color.Red)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("April 2024")

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(expenses) { expense ->
                ExpenseHistoryItem(expense = expense)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ExpenseHistoryScreenPreview(){
    ExpenseHistoryScreen()
}
