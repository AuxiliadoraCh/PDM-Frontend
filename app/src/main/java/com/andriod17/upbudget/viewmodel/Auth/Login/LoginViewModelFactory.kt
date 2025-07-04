package com.andriod17.upbudget.viewmodel.Auth.Login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andriod17.upbudget.data.local.SessionManager
import com.andriod17.upbudget.data.repository.Auth.AuthRepositoryImpl

class LoginViewModelFactory(
    private val authRepository: AuthRepositoryImpl,
    private val sessionManager: SessionManager
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(authRepository, sessionManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}