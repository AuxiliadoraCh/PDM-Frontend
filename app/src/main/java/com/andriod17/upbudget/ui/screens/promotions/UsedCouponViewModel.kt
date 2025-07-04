package com.andriod17.upbudget.ui.screens.promotions

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

    fun loadUsedCoupons(userId: String) {
        viewModelScope.launch {
            usedCouponRepository.getUsedCoupons(userId).collect { resource ->
                when (resource) {
                    is Resource.Loading -> _loading.value = true
                    is Resource.Success -> {
                        _loading.value = false
                        val coupons = resource.data

                        val fullCoupons = coupons.mapNotNull { coupon ->
                            val promo = promotionRepository.getPromotionById(coupon.promotion_id).firstOrNull()
                            when (promo) {
                                is Resource.Success -> UsedCouponWithPromotion(
                                    couponCode = coupon.code,
                                    usedAt = coupon.used_at,
                                    promotion = promo.data!!
                                )
                                else -> null
                            }
                        }

                        _usedCoupons.value = fullCoupons
                    }
                    is Resource.Error -> {
                        _loading.value = false
                    }
                }
            }
        }
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

