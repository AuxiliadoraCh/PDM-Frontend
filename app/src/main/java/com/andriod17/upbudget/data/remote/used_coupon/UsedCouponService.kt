package com.andriod17.upbudget.data.remote.used_coupon

import com.andriod17.upbudget.data.model.Used_Coupons.Requests.UsedCouponRequest
import com.andriod17.upbudget.data.remote.responses.UsedCouponResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UsedCouponService{

    @GET("used_coupons/")
    suspend fun getUsedCoupons(): Response<List<UsedCouponResponse>>

    @POST("used_coupons/")
    suspend fun registerCouponUsage(@Body request: UsedCouponRequest): Response<UsedCouponResponse>

}