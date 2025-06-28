package com.andriod17.upbudget.data.model.Promotion

data class Promotion(
    val title:String,
    val subtitle: String,
    val description: String,
    val imageResId:Int,
    val restaurantList: List<String> = emptyList(),
    val couponCode: String = "",
    val isActive: Boolean = true
)