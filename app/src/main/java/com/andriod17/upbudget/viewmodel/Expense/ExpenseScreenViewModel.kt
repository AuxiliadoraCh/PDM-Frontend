package com.andriod17.upbudget.viewmodel.Expense

import androidx.lifecycle.ViewModel
import com.andriod17.upbudget.data.model.Expense.ExpenseUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ExpenseScreenViewModel : ViewModel() {
        private val _uiState = MutableStateFlow(ExpenseUi())
        val uiState: StateFlow<ExpenseUi> = _uiState

        private val _expenses = MutableStateFlow<List<ExpenseUi>>(emptyList())
        val expenses: StateFlow<List<ExpenseUi>> = _expenses

        val incomeTotal: Double
            get() = _expenses.value.filter { it.isIncome }.sumOf { it.amount.toDoubleOrNull() ?: 0.0 }

        val expenseTotal: Double
            get() = _expenses.value.filter { !it.isIncome }.sumOf { it.amount.toDoubleOrNull() ?: 0.0 }

        val balance: Double
            get() = incomeTotal - expenseTotal


    fun onAmountChange(amount: String) {
        _uiState.value = _uiState.value.copy(amount = amount)
    }

    fun onPaymentMethodChange(paymentMethod: String) {
        _uiState.value = _uiState.value.copy(paymentMethod = paymentMethod)
    }

    fun onPlaceChange(place: String) {
        _uiState.value = _uiState.value.copy(place = place)
    }

    fun onCategoryChange(category: String) {
        _uiState.value = _uiState.value.copy(category = category)
    }

    fun onDescriptionChange(description: String) {
        _uiState.value = _uiState.value.copy(description = description)
    }

    fun onSaveDetailsChange(saveDetails: Boolean) {
        _uiState.value = _uiState.value.copy(saveExpense = saveDetails)
    }

    fun saveExpense(amount: String, category: String, description: String, isIncome: Boolean) {
        if (amount.isBlank() || category.isBlank()) return

        val newExpense = ExpenseUi(
            amount = amount,
            category = category,
            description = description,
            isIncome = isIncome
        )
        _expenses.value += newExpense
    }
}
