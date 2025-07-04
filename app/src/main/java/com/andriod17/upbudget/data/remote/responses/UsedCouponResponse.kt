package com.andriod17.upbudget.data.remote.responses

import com.andriod17.upbudget.data.database.entities.UsedCouponEntity
import com.andriod17.upbudget.data.model.Used_Coupons.UsedCoupon
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class UsedCouponResponse(
    val id: Int,
    val user_id: String,
    val promotion_id: Int,
    val code: String,
    val used_at: String
){
    fun parseDate(): Date {
        return try {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            val dateWithoutTz = used_at.split("+")[0]
            dateFormat.parse(dateWithoutTz) ?: Date()
        } catch (e: Exception) {
            Date()
        }
    }
}

fun UsedCouponResponse.toDomain(): UsedCoupon{
    return UsedCoupon(
        id = id,
        user_id = user_id,
        promotion_id = promotion_id,
        code = code,
        used_at = parseDate(),
    )
}

fun UsedCouponResponse.toEntity(): UsedCouponEntity {
    return UsedCouponEntity(
        id = id,
        user_id = user_id,
        promotion_id = promotion_id,
        code = code,
        used_at = parseDate()
    )
}



