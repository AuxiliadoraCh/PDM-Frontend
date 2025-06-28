package com.andriod17.upbudget.data.repository.Promotion

import com.andriod17.upbudget.data.model.Promotion.PromotionItem
import com.andriod17.upbudget.helpers.Resource
import kotlinx.coroutines.flow.Flow


interface PromotionRepository {

    suspend fun getPromotions(): Flow<Resource<List<PromotionItem>>>
    fun getPromotionById(id: Int): Flow<Resource<PromotionItem?>>
    fun getActivePromotions(): Flow<Resource<List<PromotionItem>>>
    fun getActivePromotionsCount(): Flow<Resource<Int>>
    //fun getCurrentPromotions(currentDate: Long): Flow<Resource<List<PromotionItem>>>
    suspend fun deletePromotion(promotion: PromotionItem): Resource<Unit>
}