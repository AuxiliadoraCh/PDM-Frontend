package com.andriod17.upbudget.data.repository.Auth

import com.andriod17.upbudget.data.remote.responses.AuthResponse
import retrofit2.Response

interface AuthRepository {
    fun login(email: String, password: String): FLow<Response<AuthResponse>>
    fun register(email: String, password: String): Response<AuthResponse>
}

