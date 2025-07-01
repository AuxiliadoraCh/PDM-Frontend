package com.andriod17.upbudget.data.remote.promotion

import com.andriod17.upbudget.data.model.Promotion.PromotionItem
import com.andriod17.upbudget.data.model.Promotion.Request.PromotionStatus
import com.andriod17.upbudget.data.remote.responses.PromotionResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface PromotionService {

    @GET("promotions")
    suspend fun getPromotions(): Response<List<PromotionResponse>>

    @GET("promotions/actives")
    suspend fun getActivePromotions(): Response<List<PromotionResponse>>

    @GET("promotions/{id}")
    suspend fun getPromotionById(@Path("id") id:Int): Response<PromotionResponse>

    @GET("promotions/count")
    suspend fun getActivePromotionsCount(): Response<Int>

    @DELETE("promotions/{id}")
    suspend fun deletePromotion(@Path("id") id:Int): Response<Unit>

    @PATCH("promotions/{id}/status")
    suspend fun updatePromotionStatus(@Path("id") id: Int, @Body request: PromotionStatus): Response<PromotionResponse>
}