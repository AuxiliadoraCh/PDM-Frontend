package com.andriod17.upbudget.data.remote.responses

import com.andriod17.upbudget.data.database.entities.CategoryEntity
import com.andriod17.upbudget.data.model.Category.Category

data class CategoryResponse (
    val id:Int,
    val name: String,
    val user_id: String? = null
)

fun CategoryResponse.toDomain(): Category{
    return Category(
        id = id,
        name = name,
        user_id = user_id
    )
}

fun CategoryResponse.toEntity(): CategoryEntity{
    return CategoryEntity(
        id = id,
        name = name,
        user_id = user_id
    )
}


