package com.andriod17.upbudget.ui.screens.limits

import android.annotation.SuppressLint
import android.os.Build
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalDensity
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.andriod17.upbudget.data.model.Category.CategoryUi
import com.andriod17.upbudget.data.model.Expense.ExpenseUi
import com.andriod17.upbudget.ui.components.CategoryBlocks
import com.andriod17.upbudget.ui.components.CustomScaffold
import com.andriod17.upbudget.viewmodel.Category.CategoryViewModel
import com.andriod17.upbudget.viewmodel.Expense.ExpenseScreenViewModel

@Composable
fun SetLimitScreen(
    categoryViewModel: CategoryViewModel = viewModel(),
    expenseViewModel: ExpenseScreenViewModel = viewModel(),
    onCategoryClick: (String) -> Unit
) {
    val categoryState by categoryViewModel.uiState.collectAsState()
    val allExpenses by expenseViewModel.allExpenses.collectAsState()

    val spentByCategory = categoryViewModel.calculateSpentByCategory(allExpenses)
    val navController = rememberNavController()

    CustomScaffold(title = "Monthly Spending Limit", navController = navController) { innerPadding ->
        SetLimitScreenContent(
            categories = categoryState.categories,
            spentByCategory = spentByCategory,
            onCategoryClick = onCategoryClick,
            padding = innerPadding
        )
    }
}


@Composable
fun SetLimitScreenContent(
    categories: List<CategoryUi>,
    spentByCategory: Map<String, Double>,
    onCategoryClick: (String) -> Unit,
    padding: PaddingValues
) {
    LazyColumn(
        modifier = Modifier
            .padding(padding)
            .padding(top = 20.dp)
            .padding( horizontal = 16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        items(categories) { category ->
            val spent = spentByCategory[category.name] ?: 0.0
            CategoryBlocks(
                categoryName = category.name,
                categoryIcon = category.iconResId,
                currentLimit = if (category.limit != null) {
                    "$${"%.2f".format(category.limit)}"
                } else {
                    "Not set"
                },
                onCategoryClick = { onCategoryClick(category.name) }
            )

        }
    }
}


@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true, showSystemUi = true     )
@Composable
fun PreviewSetLimitScreen() {
    val expenseViewModel = ExpenseScreenViewModel().apply {
        setExpensesForPreview(listOf(
            ExpenseUi(amount = "50.00", category = "Food", isIncome = false, date = "2025-06-28"),
            ExpenseUi(amount = "25.00", category = "Food", isIncome = false, date = "2025-06-27"),
            ExpenseUi(amount = "10.00", category = "Health", isIncome = false, date = "2025-06-26"),
            ExpenseUi(amount = "120.00", category = "Travel", isIncome = false, date = "2025-06-25"),
            ExpenseUi(amount = "80.00", category = "Food", isIncome = false, date = "2025-05-15") // Gasto de otro mes
        ))
    }

    SetLimitScreen(
        expenseViewModel = expenseViewModel, // Pasa el ViewModel con los datos de prueba
        onCategoryClick = { category -> println("Category clicked: $category") }
    )
}