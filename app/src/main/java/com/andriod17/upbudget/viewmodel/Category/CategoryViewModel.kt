package com.andriod17.upbudget.viewmodel.Category

import androidx.lifecycle.ViewModel
import com.andriod17.upbudget.R
import com.andriod17.upbudget.data.model.Category.CategoryUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class CategoryUiState(
    val categories: List<CategoryUi> = emptyList()
)

class CategoryViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
        CategoryUiState(
            categories = listOf(
                CategoryUi(1, "Food", R.drawable.ic_food),
                CategoryUi(2, "Health", R.drawable.ic_health),
                CategoryUi(3, "Education", R.drawable.ic_books),
                CategoryUi(4, "Travel", R.drawable.ic_travel),
                CategoryUi(5, "Internet", R.drawable.ic_wifi),
                CategoryUi(6, "Transport", R.drawable.ic_car),
                CategoryUi(7, "Technology", R.drawable.ic_phone),
                CategoryUi(8, "Beauty", R.drawable.ic_cosmetics),
                CategoryUi(9, "Clothes", R.drawable.ic_clothes)
            )
        )
    )
    val uiState: StateFlow<CategoryUiState> = _uiState
}