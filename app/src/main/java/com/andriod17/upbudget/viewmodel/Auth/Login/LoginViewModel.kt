package com.andriod17.upbudget.viewmodel.Auth.Login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.andriod17.upbudget.data.repository.Auth.AuthRepository
import com.andriod17.upbudget.helpers.Resource
import com.andriod17.upbudget.ui.navigation.RegisterNavigation
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _loginState = MutableStateFlow<Resource<String>>(Resource.Loading())
    val loginState: StateFlow<Resource<String>> = _loginState

    fun login(email : String,password : String, onLoginSuccess: () -> Unit) = viewModelScope.launch {
        authRepository.login(email = email, password = password).collectLatest { result ->
            when(result) {
                is Resource.Loading -> {
                    _loading.value = true
                }

                is Resource.Success -> {
                    Log.d("Auth", "Login successful: ${result.data?.user?.uid}")
                    _loading.value = false
                    onLoginSuccess()
                }

                is Resource.Error -> {
                    _loading.value = false
                }
            }
        }
    }

    fun logInWithGoogle() {
        _loginState.value = Resource.Loading()
        // Implementar Google login aquí
    }

    fun onRegisterPromptClick(navController: NavController) {
        navController.navigate(RegisterNavigation)
    }

    fun onForgotPasswordClick() {
        // Navegar a pantalla de recuperación de contraseña
    }

    fun handleLoginButtonClick(email: String, password: String) {
        loginUser(email, password)
    }
}