package com.andriod17.upbudget.data.model.Expense

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

data class ExpenseUi(
    val amount: String = "",
    val paymentMethod: String = "",
    val place: String = "",
    val category: String = "",
    val description: String = "",
    val date: String = "",
    val isSaving: Boolean = false,
    val isIncome: Boolean,
    val showDatePicker: Boolean = false,
    val selectedTabIndex: Int = 0,
    val selectedDateMillis: Long? = null
)

fun getCurrentDateCompat(): String {
    val currentDate = Calendar.getInstance().time
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return formatter.format(currentDate)
}