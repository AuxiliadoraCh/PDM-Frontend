package com.andriod17.upbudget.data.model.Home

data class HomeUi(
    val income: Double = 0.0,
    val spent: Double = 0.0,
    val username: String = ""
) {
    val progress: Float
        get() = if (income > 0.0) (spent / income).toFloat().coerceIn(0f, 1f) else 0f
}
