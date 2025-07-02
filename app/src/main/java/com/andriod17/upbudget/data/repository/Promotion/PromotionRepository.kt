package com.andriod17.upbudget.data.repository.Promotion

import com.andriod17.upbudget.data.model.Promotion.Promotion
import com.andriod17.upbudget.helpers.Resource
import kotlinx.coroutines.flow.Flow


interface PromotionRepository {

    suspend fun getPromotions(): Flow<Resource<List<Promotion>>>
    fun getPromotionById(id: Int): Flow<Resource<Promotion?>>
    fun getActivePromotions(): Flow<Resource<List<Promotion>>>
    fun getActivePromotionsCount(): Flow<Resource<Int>>
    //fun getCurrentPromotions(currentDate: Long): Flow<Resource<List<PromotionItem>>>
    suspend fun deletePromotion(promotion: Promotion): Resource<Unit>
    suspend fun updatePromotionStatus(promotionId: Int, isActive: Boolean): Resource<Promotion>
}