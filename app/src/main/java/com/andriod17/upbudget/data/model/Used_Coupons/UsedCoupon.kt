package com.andriod17.upbudget.data.model.Used_Coupons

import java.util.Date

data class UsedCoupon (
    val id: Int = 0,
    val user_id : String,
    val promotion_id : Int,
    val code: String,
    val used_at: Date
)