package com.andriod17.upbudget.data.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.andriod17.upbudget.data.model.Income.Income
import com.andriod17.upbudget.data.model.Income.Requests.IncomeRequest
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Entity(tableName = "incomes",
  /*  foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["user_id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["user_id"])]
   */
)
data class IncomeEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val user_id: String,
    val amount: Double,
    val date: Date,
    val description: String
)

// Nota: Esperamos que este lista la entidad de usuario para descomentar

fun IncomeEntity.toDomain(): Income {
    return Income(
        id = id,
        user_id = user_id,
        amount = amount,
        date = date,
        description = description
    )
}

fun Income.toEntity(): IncomeEntity {
    return IncomeEntity(
        id = id,
        user_id = user_id,
        amount = amount,
        date = date,
        description = description
    )
}

fun Income.toRequest(): IncomeRequest {
        return IncomeRequest(
            amount = amount,
            description = description,
            date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(date)        )
}



