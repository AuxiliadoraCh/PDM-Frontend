package com.andriod17.upbudget.data.model.Expense


data class ExpenseItem(
    val description: String,
    val formattedAmount: String,
    val formattedDate: String,
    val categoryId: Int,
    val paymentMethodId: Int
)
