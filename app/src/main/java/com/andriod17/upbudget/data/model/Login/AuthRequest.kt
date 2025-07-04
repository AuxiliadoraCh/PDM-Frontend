package com.andriod17.upbudget.data.model.Login

data class AuthRequest(val email: String, val password: String)

data class AuthResponse(
    val message: String,
    val user: UserDataWithToken
)

data class UserDataWithToken(
    val user: UserData,
    val access_token: String
)

data class UserData (
    val id: String,
    val email: String
)