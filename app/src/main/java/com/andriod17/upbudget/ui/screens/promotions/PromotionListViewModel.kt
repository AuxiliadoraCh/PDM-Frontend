package com.andriod17.upbudget.ui.screens.promotions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.andriod17.upbudget.UpBudgetApp
import com.andriod17.upbudget.data.model.Promotion.Promotion
import com.andriod17.upbudget.data.model.Promotion.PromotionItem
import com.andriod17.upbudget.data.repository.Promotion.PromotionRepository
import com.andriod17.upbudget.helpers.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PromotionListViewModel(
    private val promotionRepository: PromotionRepository
) : ViewModel() {

    private val _promotions = MutableStateFlow<List<Promotion>>(emptyList())
    val promotions: StateFlow<List<Promotion>> = _promotions

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing


    fun loadPromotions(isRefreshing: Boolean = false) {
        viewModelScope.launch {
            promotionRepository.getActivePromotions().collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        if (isRefreshing) {
                            _isRefreshing.value = true
                        } else {
                            _loading.value = true
                        }
                    }

                    is Resource.Success -> {
                        _promotions.value = resource.data
                        _loading.value = false
                        _isRefreshing.value = false
                    }

                    is Resource.Error -> {
                        _loading.value = false
                        _isRefreshing.value = false
                    }
                }
            }
        }
    }



    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as UpBudgetApp
                PromotionListViewModel(app.appProvider.providePromotionRepository())
            }
        }
    }
}
