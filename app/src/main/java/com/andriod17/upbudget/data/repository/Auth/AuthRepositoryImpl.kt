package com.andriod17.upbudget.data.repository.Auth

import com.andriod17.upbudget.data.remote.requests.AuthRequest
import com.andriod17.upbudget.data.remote.responses.AuthResponse
import com.andriod17.upbudget.data.remote.services.AuthService
import retrofit2.Response

class AuthRepositoryImpl(private val api: AuthService) : AuthRepository {

    override suspend fun login(email: String, password: String): Response<AuthResponse> {
        return api.login(AuthRequest(email, password))
    }

    override suspend fun register(email: String, password: String): Response<AuthResponse> {
        return api.register(AuthRequest(email, password))
    }
}
