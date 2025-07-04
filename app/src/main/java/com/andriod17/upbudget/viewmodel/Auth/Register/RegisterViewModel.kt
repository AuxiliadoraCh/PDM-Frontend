package com.andriod17.upbudget.viewmodel.Auth.Register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.andriod17.upbudget.data.repository.Auth.AuthRepository
import com.andriod17.upbudget.helpers.Resource
import com.andriod17.upbudget.ui.navigation.LoginNavigation
import com.andriod17.upbudget.ui.navigation.RegisterNavigation
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel(private val authRepository: AuthRepository) : ViewModel() {
    private val _loading = MutableStateFlow<Boolean>(false)
    val loading : StateFlow<Boolean> = _loading

    fun registerUser(email : String,password : String, onRegistrationSuccess: () -> Unit) =
        viewModelScope.launch {
            authRepository.register(email = email, password = password).collectLatest { result ->
                when (result) {
                    is Resource.Loading -> {
                        _loading.value = true
                    }

                    is Resource.Success -> {
                        _loading.value = false
                        onRegistrationSuccess()
                    }

                    is Resource.Error -> {
                        _loading.value = false
                        // Handle error (e.g., show a message)
                    }
                }
            }
        }
    fun onSingInPromptClick(navController: NavController) {
        navController.navigate(LoginNavigation)
    }
}
