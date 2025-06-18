package com.andriod17.upbudget.viewmodel.Register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andriod17.upbudget.data.model.Register.RegisterUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel( // private val userRepository: UserRepository //
) : ViewModel() {


    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState

    fun onUsernameChange(newUsername: String) {
        _uiState.update { it.copy(username = newUsername) }
    }

    fun onEmailChange(newEmail: String) {
        _uiState.update { it.copy(email = newEmail) }
    }

    fun onPasswordChange(newPassword: String) {
        _uiState.update { it.copy(password = newPassword) }
    }

    fun registerUser() {
        _uiState.update {
            it.copy(isLoading = true, errorMessage = null, registrationSuccess = false)
        }

        viewModelScope.launch {
            delay(2000)

            try {
                val username = _uiState.value.username
                val email = _uiState.value.email
                val password = _uiState.value.password

                if (username.isBlank() || email.isBlank() || password.isBlank()) {
                    throw IllegalArgumentException("Todos los campos son obligatorios.")
                }
                if (!email.contains("@")) {
                    throw IllegalArgumentException("Formato de correo inválido.")
                }
                if (password.length < 6) {
                    throw IllegalArgumentException("La contraseña debe tener al menos 6 caracteres.")
                }

                // userRepository.register(username, email, password)

                _uiState.update { it.copy(isLoading = false, registrationSuccess = true) }

            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isLoading = false, errorMessage = e.message ?: "Error en el registro.")
                }
            }
        }
    }

    fun signInWithGoogle() {
        _uiState.update {
            it.copy(errorMessage = "Google Sign-In aún no implementado.")
        }
    }

    fun onSignInPromptClick() {
        _uiState.update {
            it.copy(errorMessage = "Navegando a pantalla de inicio de sesión...")
        }
    }

    fun clearErrorMessage() {
        _uiState.update {
            it.copy(errorMessage = null)
        }
    }

    fun resetState() {
        _uiState.value = RegisterUiState()
    }
}
