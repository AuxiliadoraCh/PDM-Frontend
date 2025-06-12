package com.andriod17.upbudget.viewmodel.Learning

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andriod17.upbudget.data.model.Learning.LearningItem
import com.andriod17.upbudget.data.repository.Learning.LearningRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LearningViewModel (
    private val repository: LearningRepository
): ViewModel(){
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    private val _learningItems = MutableStateFlow<List<LearningItem>>(emptyList())
    val learningItems: StateFlow<List<LearningItem>> = _learningItems
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun loadLearningItems(){
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val items = repository.getLearningItemsFromDB()
                _learningItems.value = items
            }catch (e: Exception){
                _error.value = e.message
            }finally {
                _isLoading.value = false
            }
        }
    }

}