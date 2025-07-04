package com.andriod17.upbudget.data.repository.Auth

import com.andriod17.upbudget.data.remote.responses.AuthResponse
import kotlinx.coroutines.flow.Flow
import com.andriod17.upbudget.helpers.Resource

interface AuthRepository {
    fun login(email: String, password: String): Flow<Resource<AuthResponse>>
    fun register(email: String, password: String): Flow<Resource<AuthResponse>>
}

