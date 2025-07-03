package com.andriod17.upbudget.viewmodel.Auth.Login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.andriod17.upbudget.data.local.SessionManager
import com.andriod17.upbudget.data.repository.Auth.AuthRepositoryImpl
import com.andriod17.upbudget.helpers.Resource
import com.andriod17.upbudget.ui.navigation.RegisterNavigation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepositoryImpl,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _loading = MutableStateFlow<Boolean>(false)
    val loading: StateFlow<Boolean> = _loading

    fun login(email: String, password: String, onLoginSuccess: () -> Unit) = viewModelScope.launch {
        authRepository.login(email = email, password = password).collectLatest { result ->
            when (result) {
                is Resource.Loading -> {
                    _loading.value = true
                }

                is Resource.Success -> {
                    _loading.value = false
                    // Almacenar el token y el userId en SessionManager después de un login exitoso
                    sessionManager.saveAuthToken(result.data?.user?.access_token ?: "")
                    sessionManager.saveUserId(result.data?.user?.user?.id ?: "")
                    onLoginSuccess()  // Ejecutar el callback de éxito
                }

                is Resource.Error -> {
                    _loading.value = false
                }
            }
        }
    }

    fun logInWithGoogle() {
        _loading.value = true
        // Implementar Google login aquí
    }

    fun onRegisterPromptClick(navController: NavController) {
        navController.navigate(RegisterNavigation)
    }

    fun onForgotPasswordClick() {
        // Navegar a pantalla de recuperación de contraseña
    }
}
