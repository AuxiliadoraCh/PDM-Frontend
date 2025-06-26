package com.andriod17.upbudget.ui.screens.expenses
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.andriod17.upbudget.R
import com.andriod17.upbudget.data.model.Expense.ExpenseUi
import com.andriod17.upbudget.ui.components.BalanceSummarySection
import com.andriod17.upbudget.ui.components.CustomScaffold
import com.andriod17.upbudget.ui.components.ExpenseHistoryItem
import com.andriod17.upbudget.ui.components.TimePeriodSelector
import com.andriod17.upbudget.viewmodel.Category.CategoryViewModel
import com.andriod17.upbudget.viewmodel.Expense.ExpenseScreenViewModel

@Composable
fun ExpenseHistoryScreen(
    viewModel: ExpenseScreenViewModel
) {
    var selectedPeriod by remember { mutableStateOf("Mes Actual") }

    val allExpenses by viewModel.expenses.collectAsState()
    var filteredExpenses by remember { mutableStateOf(listOf<ExpenseUi>()) }

    // Calcular ingresos, egresos y balance usando allExpenses, no filteredExpenses
    val income = allExpenses.filter { it.isIncome }.sumOf { it.amount.toDoubleOrNull() ?: 0.0 }
    val expense = allExpenses.filter { !it.isIncome }.sumOf { it.amount.toDoubleOrNull() ?: 0.0 }
    val balance = income - expense

    val categoryViewModel: CategoryViewModel = viewModel()
    val navController = rememberNavController()

    LaunchedEffect(selectedPeriod, allExpenses) {
        filteredExpenses = viewModel.getFilteredExpensesByPeriod(selectedPeriod, allExpenses)
    }

    CustomScaffold(title = "Expense History", navController = navController) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .background(Color.White)
        ) {
            BalanceSummarySection(
                balanceAmount = "$${"%.2f".format(balance)}",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(12.dp),
                shape = RoundedCornerShape(15.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 50.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Income",
                            color = Color(0xFF4D9955),
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontFamily = FontFamily(Font(R.font.nunito_regular)),
                            )
                        )
                        Text(
                            text = "$${"%.2f".format(income)}",
                            color = Color.Black,
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.nunito_regular)),
                            )
                        )
                    }

                    Divider(
                        color = Color.Gray,
                        thickness = 2.dp,
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(1.dp)
                    )
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Expense",
                            color = Color(0xFFBA1A1A),
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontFamily = FontFamily(Font(R.font.nunito_regular)),
                            )
                        )
                        Text(
                            text = "$${"%.2f".format(expense)}",
                            color = Color.Black,
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.nunito_regular)),
                            )
                        )
                    }
                }
            }

            TimePeriodSelector(
                selectedPeriod = selectedPeriod,
                onPeriodSelected = { period ->
                    selectedPeriod = period
                }
            )

            LazyColumn {
                items(filteredExpenses) { expense ->
                    ExpenseHistoryItem(expense = expense, categoryViewModel = categoryViewModel)
                }
            }
        }
    }
}
@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun PreviewExpenseHistoryScreenContent() {
    val viewModel = ExpenseScreenViewModel().apply {
        val exampleExpenses = listOf(
            ExpenseUi(amount = "50.00", paymentMethod = "Cash", category = "Food", description = "Lunch at restaurant", date = "2025-06-01", isIncome = false),
            ExpenseUi(amount = "200.00", paymentMethod = "Bank Transfer", category = "Education", description = "April salary", date = "2025-06-01", isIncome = true),
            ExpenseUi(amount = "15.00", paymentMethod = "Card", category = "Beauty", description = "Movie ticket", date = "2025-06-02", isIncome = false)
        )

        exampleExpenses.forEach { expense ->
            saveExpense(amount = expense.amount, category = expense.category, description = expense.description, isIncome = expense.isIncome, date = expense.date)
        }

        filterExpensesByPeriod("Mes Actual")

    }

    ExpenseHistoryScreen(viewModel = viewModel)
}


@Preview(showBackground = true)
@Composable
fun PreviewExpenseHistoryScreen() {
    PreviewExpenseHistoryScreenContent()
}
