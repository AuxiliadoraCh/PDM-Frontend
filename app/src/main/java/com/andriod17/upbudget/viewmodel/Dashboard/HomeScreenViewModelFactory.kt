package com.andriod17.upbudget.viewmodel.Dashboard


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
//import com.andriod17.upbudget.repository.UserRepository
//import com.andriod17.upbudget.repository.ExpenseRepository

class HomeScreenViewModelFactory(
    //private val userRepository: UserRepository,
    //private val expenseRepository: ExpenseRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeScreenViewModel::class.java)) {
      //      return HomeScreenViewModel(userRepository, expenseRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

