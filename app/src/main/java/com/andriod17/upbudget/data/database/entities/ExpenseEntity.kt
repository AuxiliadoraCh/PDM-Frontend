package com.andriod17.upbudget.data.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.andriod17.upbudget.data.model.Expense.Expense
import java.util.Date


@Entity(
    tableName = "expenses",
//    foreignKeys = [
//        ForeignKey(
//            entity = CategoryEntity::class,
//            parentColumns = ["id"],
//            childColumns = ["category_id"],
//            onDelete = ForeignKey.NO_ACTION
//        ),
//        ForeignKey(
//            entity = PaymentMethodEntity::class,
//            parentColumns = ["id"],
//            childColumns = ["payment_method_id"],
//            onDelete = ForeignKey.NO_ACTION
//        )
//    ],
//    indices = [
//        Index(value = ["category_id"]),
//        Index(value = ["payment_method_id"]),
//        Index(value = ["user_id"])
//    ]
)
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val user_id: String,
    val amount: Double,
    val date: Date,
    val description: String,
    val category_id: Int,
    val payment_method_id: Int
)

fun ExpenseEntity.toDomain(): Expense {
    return Expense(
        id = id,
        user_id = user_id,
        amount = amount,
        date = date,
        description = description,
        category_id = category_id,
        payment_method_id = payment_method_id
    )
}

fun Expense.toEntity(): ExpenseEntity {
    return ExpenseEntity(
        id = id,
        user_id = user_id,
        amount = amount,
        date = date,
        description = description,
        category_id = category_id,
        payment_method_id = payment_method_id
    )
}

