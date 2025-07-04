package com.andriod17.upbudget.viewmodel.Auth.Login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andriod17.upbudget.data.repository.Auth.AuthRepository

class LoginViewModelFactory(
    private val authRepository: AuthRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(authRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

