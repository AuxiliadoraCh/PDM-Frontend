package com.andriod17.upbudget.data.model.Promotion

import com.andriod17.upbudget.R
import java.util.Date

    data class Promotion (
        val id: Int = 0,
        val title:String,
        val description: String,
        val active: Boolean,
        val start_date: Date,
        val end_date: Date,
        val images: List<String>,
        val restaurants: List<String>
    )

fun Promotion.toPromotionItem(): PromotionItem {
    return PromotionItem(
        title = title,
        subtitle = if (active) "Available Now" else "Expired",
        description = description,
        imageResId = images.firstOrNull(),
        restaurantList = restaurants,
        couponCode = "CODE${this.id}",
        isActive = active
    )
}








