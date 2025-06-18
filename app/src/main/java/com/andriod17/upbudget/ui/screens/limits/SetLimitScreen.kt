package com.andriod17.upbudget.ui.screens.limits

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
import com.andriod17.upbudget.data.model.Category.CategoryUi
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
    val expenses by expenseViewModel.expenses.collectAsState()

    val spentByCategory = categoryViewModel.calculateSpentByCategory(expenses)

    CustomScaffold(title = "Monthly Spending Limit") { innerPadding ->
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
                    val limit = spentByCategory[category.name] ?: 0.0
                    "$${"%.2f".format(category.limit)}"
                } else {
                    "Not set"
                },
                onCategoryClick = { onCategoryClick(category.name) }
            )

        }
    }
}


@Preview(showBackground = true, showSystemUi = true     )
@Composable
fun PreviewSetLimitScreen() {
    SetLimitScreen(
        onCategoryClick = { category -> println("Category clicked: $category") }
    )
}
