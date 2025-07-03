package com.andriod17.upbudget.viewmodel.Auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andriod17.upbudget.data.local.SessionManager
import com.andriod17.upbudget.data.repository.Auth.AuthRepository
import com.andriod17.upbudget.helpers.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _authState = MutableStateFlow<Resource<String>>(Resource.Loading())
    val authState: StateFlow<Resource<String>> = _authState

    fun login(email: String, password: String) {
        _authState.value = Resource.Loading()

        viewModelScope.launch {
            try {
                val response = repository.login(email, password)
                if (response.isSuccessful) {
                    response.body()?.let {
                        sessionManager.saveAuthToken(it.user.access_token)
                        sessionManager.saveUserId(it.user.user.id)
                        _authState.value = Resource.Success("Login exitoso")
                    }
                } else {
                    _authState.value = Resource.Error("Credenciales inválidas")
                }
            } catch (e: Exception) {
                _authState.value = Resource.Error("Error: ${e.message}")
            }
        }
    }

    fun register(email: String, password: String) {
        _authState.value = Resource.Loading()

        viewModelScope.launch {
            try {
                val response = repository.register(email, password)
                if (response.isSuccessful) {
                    response.body()?.let {
                        sessionManager.saveAuthToken(it.user.access_token)
                        sessionManager.saveUserId(it.user.user.id)
                        _authState.value = Resource.Success("Registro exitoso")
                    }
                } else {
                    _authState.value = Resource.Error("Error en el registro")
                }
            } catch (e: Exception) {
                _authState.value = Resource.Error("Error: ${e.message}")
            }
        }
    }
}
