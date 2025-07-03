package com.andriod17.upbudget.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.andriod17.upbudget.data.model.Category.Category
import com.andriod17.upbudget.data.remote.responses.CategoryResponse

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val user_id:String? = null
)

fun CategoryEntity.toDomain(): Category{
    return Category(
        id = id,
        name = name,
        user_id = user_id
    )
}

fun Category.toEntity(): CategoryEntity{
    return CategoryEntity(
        id = id,
        name = name,
        user_id = user_id
    )
}

fun CategoryResponse.toEntity(): CategoryEntity {
    return CategoryEntity(
        id = id,
        name = name,
        user_id = user_id
    )
}

