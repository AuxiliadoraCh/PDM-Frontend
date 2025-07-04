package com.andriod17.upbudget.data.model.Expense

enum class TransactionTab(val label: String, val isIncome: Boolean) {
    Income("Income", true),
    Expense("Expense", false)
}