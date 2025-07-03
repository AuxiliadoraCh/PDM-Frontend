package com.andriod17.upbudget.data.repository.Auth

import com.andriod17.upbudget.data.remote.requests.AuthRequest
import com.andriod17.upbudget.data.remote.responses.AuthResponse
import com.andriod17.upbudget.data.remote.services.AuthService
import com.andriod17.upbudget.helpers.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class AuthRepositoryImpl(private val api: AuthService) : AuthRepository {
    // Implementación del login usando Flow
    override fun login(email: String, password: String): Flow<Resource<AuthResponse>> = flow {
        // Emitimos el estado de carga
        emit(Resource.Loading)

        try {
            val response = api.login(AuthRequest(email, password))

            if (response.isSuccessful) {
                // Emitimos el éxito con los datos de la respuesta
                response.body()?.let {
                    emit(Resource.Success(it))
                } ?: emit(Resource.Error("Login failed: No data received"))
            } else {
                // En caso de error, emitimos el estado de error
                emit(Resource.Error("Login failed: ${response.message()}"))
            }
        } catch (exception: Exception) {
            // En caso de una excepción, emitimos el error
            emit(Resource.Error("Login failed: ${exception.localizedMessage}"))
        }
    }

    // Implementación del registro usando Flow
    override fun register(email: String, password: String): Flow<Resource<AuthResponse>> = flow {
        // Emitimos el estado de carga
        emit(Resource.Loading)

        try {
            // Realizamos la llamada a la API
            val response = api.register(AuthRequest(email, password))

            // Verificamos la respuesta
            if (response.isSuccessful) {
                // Emitimos el éxito con los datos de la respuesta
                response.body()?.let {
                    emit(Resource.Success(it))
                } ?: emit(Resource.Error("Registration failed: No data received"))
            } else {
                // En caso de error, emitimos el estado de error
                emit(Resource.Error("Registration failed: ${response.message()}"))
            }
        } catch (exception: Exception) {
            // En caso de una excepción, emitimos el error
            emit(Resource.Error("Registration failed: ${exception.localizedMessage}"))
        }
    }
}

