package com.andriod17.upbudget.viewmodel.Learning

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andriod17.upbudget.data.repository.Learning.LearningRepository

class LearningViewModelFactory(
    private val repository: LearningRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LearningViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LearningViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}

