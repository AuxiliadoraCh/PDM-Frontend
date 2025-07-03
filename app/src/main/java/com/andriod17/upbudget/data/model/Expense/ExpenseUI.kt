package com.andriod17.upbudget.data.model.Expense

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

data class ExpenseUi(
    val amount: String = "",
    val paymentMethod: String = "",
    val balance: Double = 0.0,
    val place: String = "",
    val category: String = "",
    val description: String = "",
    val date: String = getCurrentDateCompat(),
    val saveExpense: Boolean = false,
    val isSaving: Boolean = false,
    val isIncome: Boolean = false 
)

fun getCurrentDateCompat(): String {
    val currentDate = Calendar.getInstance().time
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return formatter.format(currentDate)
}
