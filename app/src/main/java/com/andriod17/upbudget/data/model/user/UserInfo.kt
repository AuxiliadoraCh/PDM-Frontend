package com.andriod17.upbudget.data.model.user

data class UserInfo(
    val name: String,
    val username: String,
    val email: String,
    val imageUrl: String,
    val registeredDate: String,
    val totalTransactions: Int,
    val lastLogin: String
)

