package com.andriod17.upbudget.viewmodel.Login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andriod17.upbudget.data.model.Login.LoginUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    // private val userRepository: UserRepository // ← lo dejas comentado si aún no usas Room
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    fun onEmailChange(newEmail: String) {
        _uiState.update { it.copy(email = newEmail) }
    }

    fun onPasswordChange(newPassword: String) {
        _uiState.update { it.copy(password = newPassword) }
    }

    fun loginUser() {
        _uiState.update {
            it.copy(isLoading = true, errorMessage = null, loginSuccess = false)
        }

        viewModelScope.launch {
            delay(1000)

            try {
                val email = _uiState.value.email
                val password = _uiState.value.password

                if (email.isBlank() || password.isBlank()) {
                    throw IllegalArgumentException("Todos los campos son obligatorios.")
                }

                if (!email.contains("@")) {
                    throw IllegalArgumentException("Correo inválido.")
                }

                if (password.length < 6) {
                    throw IllegalArgumentException("Contraseña muy corta.")
                }

                _uiState.update {
                    it.copy(isLoading = false, loginSuccess = true)
                }

            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isLoading = false, errorMessage = e.message)
                }
            }
        }
    }
    fun logInWithGoogle(){
        _uiState.update {
            it.copy(isLoading = true, errorMessage = null, loginSuccess = false)
        }
    }

    fun clearErrorMessage() {
        _uiState.update { it.copy(errorMessage = null) }
    }

    fun resetState() {
        _uiState.value = LoginUiState()
    }
}
