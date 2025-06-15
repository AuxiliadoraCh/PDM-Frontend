package com.andriod17.upbudget.viewmodel.Dashboard

import androidx.lifecycle.ViewModel
import com.andriod17.upbudget.data.model.Home.HomeUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeScreenViewModel(
    // val userRepository: UserRepository
    // val expenseRepository: ExpenseRepository

) : ViewModel() {
    private val _uiState = MutableStateFlow(
        HomeUi(
            //income = 2500.0,
            //spent = 1732.0,
            //username = "Rebe"
        )
    )
    val uiState: StateFlow<HomeUi> = _uiState

    init {
        // _uiState.value = HomeUiState(income = 2500.0, username = "Rebe")
    }
    /*
    fun loadData(userId: Int) {
        viewModelScope.launch {
            val user = userRepository.getUser(userId)
            val expenses = expenseRepository.getTotalSpent(userId)
            _uiState.value = HomeUi(
                income = user.income,
                spent = expenses,
                username = user.name
            )
        }
    }
    */
}