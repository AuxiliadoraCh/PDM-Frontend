package com.andriod17.upbudget.data.model.Expense

import java.text.SimpleDateFormat
import java.util.*

data class ExpenseUi(
    val amount: String = "",
    val paymentMethod: String = "",
    val place: String = "",
    val category: String = "",
    val description: String = "",
    val date: String = getCurrentDateCompat(),
    val saveExpense: Boolean = false,
    val isSaving: Boolean = false
)

fun getCurrentDateCompat(): String {
    val currentDate = Calendar.getInstance().time
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return formatter.format(currentDate)
}

