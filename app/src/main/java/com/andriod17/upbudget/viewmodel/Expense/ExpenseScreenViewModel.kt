package com.andriod17.upbudget.viewmodel.Expense

import androidx.lifecycle.ViewModel
import com.andriod17.upbudget.data.model.Expense.ExpenseUi
import com.andriod17.upbudget.data.model.Expense.getCurrentDateCompat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ExpenseScreenViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ExpenseUi())
    val uiState: StateFlow<ExpenseUi> = _uiState

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

    fun saveExpense() {
        val expense = _uiState.value.copy(date = getCurrentDateCompat())
    }
}
