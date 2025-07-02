package com.andriod17.upbudget.data.remote.responses

import com.andriod17.upbudget.data.database.entities.BudgetsEntity
import com.andriod17.upbudget.data.model.Budgets.Budgets

data class BudgetsResponse (
    val id: Int,
    val user_id: String,
    val category_id: Int,
    val month: Int,
    val year: Int,
    val amount: Double
)

fun BudgetsResponse.toDomain(): Budgets{
    return Budgets(
        id = id,
        user_id = user_id,
        category_id = category_id,
        month = month,
        year = year,
        amount = amount
    )
}

fun BudgetsResponse.toEntity(): BudgetsEntity {
    return BudgetsEntity(
        id = id,
        user_id = user_id,
        category_id = category_id,
        month = month,
        year = year,
        amount = amount
    )
}