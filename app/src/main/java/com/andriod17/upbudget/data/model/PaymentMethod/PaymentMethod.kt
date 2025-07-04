package com.andriod17.upbudget.data.model.PaymentMethod

data class PaymentMethod(
    val id: Int,
    val name: String,
    val user_id: String? = null,
    val is_default: Boolean
)

