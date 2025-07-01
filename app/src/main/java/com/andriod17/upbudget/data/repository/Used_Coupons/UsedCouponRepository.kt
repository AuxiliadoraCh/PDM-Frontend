package com.andriod17.upbudget.data.repository.Used_Coupons

import com.andriod17.upbudget.data.model.Used_Coupons.UsedCoupon
import com.andriod17.upbudget.helpers.Resource
import kotlinx.coroutines.flow.Flow

interface UsedCouponRepository {
    fun getUsedCoupons(user_id: String): Flow<Resource<List<UsedCoupon>>>
    suspend fun registerCouponUsage(user_id: String, promotion_id: Int): Resource<UsedCoupon>

}