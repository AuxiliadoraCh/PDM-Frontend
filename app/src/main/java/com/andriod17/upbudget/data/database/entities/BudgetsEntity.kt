package com.andriod17.upbudget.data.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.andriod17.upbudget.data.model.Budgets.Budgets
import com.andriod17.upbudget.data.model.Budgets.Request.BudgetRequest

@Entity(tableName = "budgets",
   /* foreignKeys = [
        ForeignKey(
            entities = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entities = CategoryEntity::class,
            parentColumns = ["id"],
            childColumn = ["category_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value= ["user_id"]),
        Index(value= ["category_id"]),
        Index(value = ["user_id", "category_id"], unique= true)
    ]*/
    )
    data class BudgetsEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val user_id: String,
    val category_id: Int,
    val month: Int,
    val year: Int,
    val amount: Double
    )

fun BudgetsEntity.toDomain(): Budgets {
    return Budgets(
        id = id,
        user_id = user_id,
        category_id = category_id,
        month = month,
        year = year,
        amount = amount
    )
}

fun Budgets.toEntity(): BudgetsEntity{
    return BudgetsEntity(
        id = id,
        user_id = user_id,
        category_id = category_id,
        month = month,
        year = year,
        amount = amount
    )
}

fun Budgets.toRequest(): BudgetRequest {
    return BudgetRequest(
        category_id = category_id,
        month = month,
        year = year,
        amount = amount
    )

}