package com.andriod17.upbudget.data.remote.responses

import com.andriod17.upbudget.data.database.entities.ExpenseEntity
import com.andriod17.upbudget.data.model.Expense.Expense
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class ExpenseResponse(
    val id: Int,
    val user_id: String,
    val amount: Double,
    val date: String,
    val description: String,
    val category_id: Int,
    val payment_method_id: Int
) {
    fun parseDate(): Date {
        return try {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS", Locale.getDefault())
            val trimmedDate = date.split("+")[0]
            dateFormat.parse(trimmedDate) ?: Date()
        } catch (e: Exception) {
            Date()
        }
    }
}

fun ExpenseResponse.toDomain(): Expense {
    return Expense(
        id = id,
        user_id = user_id,
        amount = amount,
        date = parseDate(),
        description = description,
        category_id = category_id,
        payment_method_id = payment_method_id
    )
}

fun ExpenseResponse.toEntity(): ExpenseEntity {
    return ExpenseEntity(
        id = id,
        user_id = user_id,
        amount = amount,
        date = parseDate(),
        description = description,
        category_id = category_id,
        payment_method_id = payment_method_id
    )
}