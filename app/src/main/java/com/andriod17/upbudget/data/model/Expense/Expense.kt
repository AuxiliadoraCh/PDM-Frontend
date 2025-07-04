package com.andriod17.upbudget.data.model.Expense

import java.util.Date

data class Expense(
    val id: Int = 0,
    val user_id: String,
    val amount: Double,
    val date: Date,
    val description: String,
    val category_id: Int,
    val payment_method_id: Int


)

fun Expense.toExpenseItem(): ExpenseItem {
    return ExpenseItem(
        description = description,
        formattedAmount = "$" + String.format("%.2f", amount),
        formattedDate = date.toString(),
        categoryId = category_id,
        paymentMethodId = payment_method_id
    )
}