package com.andriod17.upbudget.viewmodel.Auth.Register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andriod17.upbudget.data.local.SessionManager
import com.andriod17.upbudget.data.repository.Auth.AuthRepository

class RegisterViewModelFactory(
    private val authRepository: AuthRepository,
    private val sessionManager: SessionManager
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(authRepository, sessionManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}