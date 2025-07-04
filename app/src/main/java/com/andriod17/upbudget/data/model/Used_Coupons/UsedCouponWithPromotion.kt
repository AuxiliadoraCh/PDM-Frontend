package com.andriod17.upbudget.data.model.Used_Coupons

import com.andriod17.upbudget.data.model.Promotion.Promotion
import java.util.Date

data class UsedCouponWithPromotion(
    val couponCode: String,
    val usedAt: Date,
    val promotion: Promotion
)
