package com.andriod17.upbudget.data.database.entities


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.andriod17.upbudget.data.model.PaymentMethod.PaymentMethod
import com.andriod17.upbudget.data.remote.responses.PaymentMethodResponse

@Entity(tableName = "payment_methods")
data class PaymentMethodEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val user_id: String? = null,
    val is_default: Boolean
)

fun PaymentMethodEntity.toDomain(): PaymentMethod {
    return PaymentMethod(
        id = id,
        name = name,
        user_id = user_id,
        is_default = is_default
    )
}

fun PaymentMethod.toEntity(): PaymentMethodEntity {
    return PaymentMethodEntity(
        id = id,
        name = name,
        user_id = user_id,
        is_default = is_default
    )
}

fun PaymentMethodResponse.toEntity(): PaymentMethodEntity {
    return PaymentMethodEntity(
        id = id,
        name = name,
        user_id = user_id,
        is_default = is_default
    )
}
