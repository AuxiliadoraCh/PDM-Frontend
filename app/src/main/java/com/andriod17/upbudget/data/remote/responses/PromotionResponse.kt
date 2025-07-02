package com.andriod17.upbudget.data.remote.responses

import com.andriod17.upbudget.data.database.entitites.PromotionEntity
import com.andriod17.upbudget.data.model.Promotion.Promotion
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class PromotionResponse (
    val id: Int,
    val title: String,
    val description: String,
    val active: Boolean,
    val start_date: String,
    val end_date: String,
    val created_at: String,
    val images: List<String>,
    val restaurants: List<String>
)

fun PromotionResponse.toDomain(): Promotion{
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return Promotion(
        id=id,
        title = title,
        description = description,
        active = active,
        start_date = dateFormat.parse(start_date) ?: Date(),
        end_date = dateFormat.parse(end_date) ?: Date(),
        images = images,
        restaurants = restaurants
    )
}

fun PromotionResponse.toEntity(): PromotionEntity{
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    return PromotionEntity(
        id = id,
        title = title,
        description = description,
        active = active,
        start_date = dateFormat.parse(start_date) ?: Date(),
        end_date = dateFormat.parse(end_date) ?: Date(),
        images = images,
        restaurants = restaurants
    )
}