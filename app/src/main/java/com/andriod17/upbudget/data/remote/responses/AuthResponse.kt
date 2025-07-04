package com.andriod17.upbudget.data.remote.responses

data class AuthResponse(
    val message: String,
    val user: UserDataWithToken
)

data class UserDataWithToken(
    val user: UserData,
    val access_token: String
)

data class UserData(
    val id: String,
    val email: String
)
