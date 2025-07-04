package com.andriod17.upbudget.data.remote.services

import com.andriod17.upbudget.data.remote.requests.AuthRequest
import com.andriod17.upbudget.data.remote.responses.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST(value = "auth/login/")
    suspend fun login(@Body request: AuthRequest): Response<AuthResponse>

    @POST(value = "auth/register/")
    suspend fun register(@Body request: AuthRequest): Response<AuthResponse>
}