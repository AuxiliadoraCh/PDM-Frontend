package com.andriod17.upbudget.data.repository.Used_Coupons

import com.andriod17.upbudget.data.model.Used_Coupons.UsedCoupon
import com.andriod17.upbudget.helpers.Resource
import kotlinx.coroutines.flow.Flow

interface UsedCouponRepository {
    fun getUsedCoupons(): Flow<Resource<List<UsedCoupon>>>
    suspend fun registerCouponUsage(promotion_id: Int): Resource<UsedCoupon>

}