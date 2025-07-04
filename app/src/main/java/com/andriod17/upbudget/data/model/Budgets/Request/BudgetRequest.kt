package com.andriod17.upbudget.data.model.Budgets.Request

data class BudgetRequest (
    val category_id: Int,
    val month: Int,
    val year: Int,
    val amount: Double
)