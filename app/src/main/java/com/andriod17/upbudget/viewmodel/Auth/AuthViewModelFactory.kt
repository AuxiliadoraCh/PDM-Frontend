//package com.andriod17.upbudget.viewmodel.Auth
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.andriod17.upbudget.data.local.SessionManager
//import com.andriod17.upbudget.data.repository.Auth.AuthRepository
//
//class AuthViewModelFactory(
//    private val repository: AuthRepository,
//    private val sessionManager: SessionManager
//) : ViewModelProvider.Factory {
//
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
//            return AuthViewModel(repository, sessionManager) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}
