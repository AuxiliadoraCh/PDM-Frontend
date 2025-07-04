package com.andriod17.upbudget.data.remote.responses

import com.andriod17.upbudget.data.database.entities.PaymentMethodEntity
import com.andriod17.upbudget.data.model.PaymentMethod.PaymentMethod

data class PaymentMethodResponse(
    val id: Int,
    val name: String,
    val user_id: String? = null,
    val is_default: Boolean
)


fun PaymentMethodResponse.toDomain(): PaymentMethod {
    return PaymentMethod(
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


