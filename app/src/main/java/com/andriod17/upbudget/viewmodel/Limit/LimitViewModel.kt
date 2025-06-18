package com.andriod17.upbudget.ui.screens.limits

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class SetLimitViewModel : ViewModel() {
    // Initial value for the limit
    var currentLimit by mutableStateOf<String>("")
        private set

    // For handling input change
    fun updateLimit(newLimit: String) {
        currentLimit = newLimit
    }

    // Simulate saving to database (Room logic will be implemented later)
    fun saveLimit() {
        // Add Room integration logic here when needed
        // For now, we'll print it to the log
        println("Limit saved: $currentLimit")
    }
}
