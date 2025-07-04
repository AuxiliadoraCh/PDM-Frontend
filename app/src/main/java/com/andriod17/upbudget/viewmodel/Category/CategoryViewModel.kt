package com.andriod17.upbudget.viewmodel.Category

import androidx.lifecycle.ViewModel
import com.andriod17.upbudget.R
import com.andriod17.upbudget.data.model.Category.CategoryUi
import com.andriod17.upbudget.data.model.Expense.ExpenseUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class CategoryUiState(
    val categories: List<CategoryUi> = emptyList()
)

class CategoryViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
        CategoryUiState(
            categories = listOf(
                CategoryUi(1, "Food", R.drawable.ic_food, limit = 200.0),
                CategoryUi(2, "Health", R.drawable.ic_health, limit = 100.0),
                CategoryUi(3, "Education", R.drawable.ic_books),
                CategoryUi(4, "Travel", R.drawable.ic_travel, limit = 300.0),
                CategoryUi(5, "Internet", R.drawable.ic_wifi),
                CategoryUi(6, "Transport", R.drawable.ic_car, limit = 150.0),
                CategoryUi(7, "Technology", R.drawable.ic_phone),
                CategoryUi(8, "Beauty", R.drawable.ic_cosmetics),
                CategoryUi(9, "Clothes", R.drawable.ic_clothes)
            )
        )

    )

    val uiState: StateFlow<CategoryUiState> = _uiState
    fun calculateSpentByCategory(expenses: List<ExpenseUi>): Map<String, Double> {
        return expenses.groupBy { it.category }.mapValues { (_, list) ->
            list.sumOf { it.amount.toDoubleOrNull() ?: 0.0 }
        }
    }
    fun getCategoryIcon(categoryName: String): Int? {
        return uiState.value.categories.find { it.name == categoryName }?.iconResId
    }

}