package com.andriod17.upbudget.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: String,
    val email: String,                 // Correo del usuario
    val created_at: String?,          // Timestamp (puede ser String o Date)
    val last_sign_in_at: String?,  // Timestamp (puede ser String o Date)
)