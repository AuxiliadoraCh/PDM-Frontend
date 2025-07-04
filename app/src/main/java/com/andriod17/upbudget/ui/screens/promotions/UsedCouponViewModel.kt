package com.andriod17.upbudget.ui.screens.promotions

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.andriod17.upbudget.UpBudgetApp
import com.andriod17.upbudget.data.model.Used_Coupons.UsedCouponWithPromotion
import com.andriod17.upbudget.data.repository.Promotion.PromotionRepository
import com.andriod17.upbudget.data.repository.Used_Coupons.UsedCouponRepository
import com.andriod17.upbudget.helpers.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class UsedCouponsViewModel(
    private val usedCouponRepository: UsedCouponRepository,
    private val promotionRepository: PromotionRepository
): ViewModel() {

    private val _usedCoupons = MutableStateFlow<List<UsedCouponWithPromotion>>(emptyList())
    val usedCoupons: StateFlow<List<UsedCouponWithPromotion>> = _usedCoupons
    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _success = MutableStateFlow(false)
    val success: StateFlow<Boolean> = _success

    fun loadUsedCoupons() {
        viewModelScope.launch {
            usedCouponRepository.getUsedCoupons().collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _loading.value = true
                        _error.value = null
                    }
                    is Resource.Success -> {
                        _loading.value = false
                        _error.value = null
                        val coupons = resource.data

                        Log.d("UsedCouponsViewModel", "Processing ${coupons.size} coupons")

                        val fullCoupons = mutableListOf<UsedCouponWithPromotion>()

                        for (coupon in coupons) {
                            try {
                                Log.d("UsedCouponsViewModel", "Fetching promotion ${coupon.promotion_id} for coupon ${coupon.code}")

                                val promotionResult = promotionRepository.getPromotionById(coupon.promotion_id)
                                    .first { it !is Resource.Loading }

                                when (promotionResult) {
                                    is Resource.Success -> {
                                        if (promotionResult.data != null) {
                                            val couponWithPromotion = UsedCouponWithPromotion(
                                                couponCode = coupon.code,
                                                usedAt = coupon.used_at,
                                                promotion = promotionResult.data
                                            )
                                            fullCoupons.add(couponWithPromotion)
                                            Log.d("UsedCouponsViewModel", "Successfully loaded promotion: ${promotionResult.data.title}")
                                        }
                                    }
                                    is Resource.Error -> {
                                        Log.d("UsedCouponsViewModel", "Failed to load promotion ${coupon.promotion_id}: ${promotionResult.message}")
                                    }
                                    else -> {
                                        Log.d("UsedCouponsViewModel", "Unexpected result for promotion ${coupon.promotion_id}")
                                    }
                                }
                            } catch (e: Exception) {
                                Log.d("UsedCouponsViewModel", "Exception processing coupon ${coupon.id}: ${e.message}")
                            }
                        }

                        Log.d("UsedCouponsViewModel", "Final coupons with promotions: ${fullCoupons.size}")
                        _usedCoupons.value = fullCoupons
                    }
                    is Resource.Error -> {
                        _loading.value = false
                        _error.value = resource.message
                        Log.d("UsedCouponsViewModel", "Error loading coupons: ${resource.message}")
                    }
                }
            }
        }
    }

    fun registerCouponUsage(promotionId: Int) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            _success.value = false

            Log.d("UsedCouponsViewModel", "Attempting to register coupon for promotion: $promotionId")

            try {
                val result = usedCouponRepository.registerCouponUsage(promotionId)

                when (result) {
                    is Resource.Success -> {
                        _loading.value = false
                        _success.value = true
                        Log.d("UsedCouponsViewModel", "Coupon successfully registered")
                    }
                    is Resource.Error -> {
                        _loading.value = false
                        _error.value = result.message
                    }
                    is Resource.Loading -> {
                        _loading.value = true
                    }
                }
            } catch (e: Exception) {
                _loading.value = false
                _error.value = "Error inesperado: ${e.message}"
                Log.e("UsedCouponsViewModel", "Unexpected error: ${e.message}", e)
            }
        }
    }

    fun clearError() {
        _error.value = null
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as UpBudgetApp
                UsedCouponsViewModel(
                    application.appProvider.provideUsedCouponRepository(),
                    application.appProvider.providePromotionRepository()
                )
            }
        }
    }
}

