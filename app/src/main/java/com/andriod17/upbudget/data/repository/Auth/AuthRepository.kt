package com.andriod17.upbudget.data.repository.Auth

import com.andriod17.upbudget.data.remote.responses.AuthResponse
import com.andriod17.upbudget.helpers.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun login(email: String, password: String): Flow<Resource<AuthResponse>>
    fun register(email: String, password: String): Flow<Resource<AuthResponse>>
}

