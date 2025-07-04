package com.andriod17.upbudget.data.model.Income

import com.andriod17.upbudget.data.database.entities.IncomeEntity
import java.util.Date

data class Income(
    val id: Int = 0,
    val user_id: String,
    val amount: Double,
    val date: Date,
    val description: String
)

fun Income.toEntity(): IncomeEntity{
    return IncomeEntity(
        id = id,
        user_id = user_id,
        amount = amount,
        date = date,
        description = description
    )
}