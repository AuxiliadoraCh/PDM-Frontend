package com.andriod17.upbudget.data.model.Expense.Request

data class ExpenseRequest(
    val user_id: String,
    val amount: Double,
    val description: String,
    val category_id: Int,
    val payment_id: Int
)

