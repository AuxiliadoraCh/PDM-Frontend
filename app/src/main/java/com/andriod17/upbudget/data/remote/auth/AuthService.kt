package com.andriod17.upbudget.data.remote.auth

import com.andriod17.upbudget.data.model.Login.AuthRequest
import com.andriod17.upbudget.data.model.Login.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("auth/login/")
    suspend fun login(@Body request: AuthRequest): Response<AuthResponse>

    @POST("auth/register/")
    suspend fun register(@Body request: AuthRequest): Response<AuthResponse>
}