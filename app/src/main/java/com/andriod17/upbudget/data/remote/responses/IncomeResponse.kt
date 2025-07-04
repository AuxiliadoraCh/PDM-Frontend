package com.andriod17.upbudget.data.remote.responses

import com.andriod17.upbudget.data.database.entities.IncomeEntity
import com.andriod17.upbudget.data.model.Income.Income
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class IncomeResponse(
    val id: Int,
    val user_id: String,
    val amount: Double,
    val date: String,
    val description: String
){

fun parseDate(): Date {
    return try {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val dateWithoutTz = date.split("+")[0]
        dateFormat.parse(dateWithoutTz) ?: Date()
    } catch (e: Exception) {
        Date()
    }
}
}

fun IncomeResponse.toDomain(): Income{
    return Income(
        id = id,
        user_id = user_id,
        amount = amount,
        date = parseDate(),
        description = description
    )
}

fun IncomeResponse.toEntity(): IncomeEntity{
    return IncomeEntity(
        id = id,
        user_id = user_id,
        amount = amount,
        date = parseDate(),
        description = description
    )
}
