package com.andriod17.upbudget.data.repository.Promotion

import android.util.Log
import com.andriod17.upbudget.data.database.dao.PromotionDao
import com.andriod17.upbudget.data.model.Promotion.Promotion
import com.andriod17.upbudget.data.remote.promotion.PromotionService
import com.andriod17.upbudget.data.remote.responses.toEntity
import com.andriod17.upbudget.data.remote.responses.toDomain
import com.andriod17.upbudget.helpers.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import com.andriod17.upbudget.data.database.entitites.toDomain
import com.andriod17.upbudget.data.database.entitites.toEntity
import com.andriod17.upbudget.data.model.Promotion.Request.PromotionStatus
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class PromotionRepositoryImpl(
    private val promotionService : PromotionService,
    private val promotionDao: PromotionDao
): PromotionRepository {

    override suspend fun getPromotions(): Flow<Resource<List<Promotion>>> = flow {
        emit(Resource.Loading)
        try {
            val remotePromotions = promotionService.getPromotions()
            if (remotePromotions.isSuccessful){
                remotePromotions.body()?.let { promotions ->
                    if (promotions.isNotEmpty()){
                        promotionDao.insertPromotions(promotions.map {it.toEntity()})
                    }
                }
            }
        } catch(e: Exception){
            Log.d("PromotionRepositoryImpl", "Error fetching remote data: ${e.message}")
        }

            val localPromotions = promotionDao.getPromotions().map{ entities ->
                val promotions = entities.map {it.toDomain()}
                if (promotions.isEmpty()){
                    Resource.Error("No promotions found")
                } else {
                    Resource.Success(promotions)
                }
            }.distinctUntilChanged()

            emitAll(localPromotions)
    }.flowOn(Dispatchers.IO)

    override fun getPromotionById(id: Int): Flow<Resource<Promotion?>> = flow {
        emit(Resource.Loading)

        try {
            val response = promotionService.getPromotionById(id)
            if (response.isSuccessful) {
                response.body()?.let { promotionResponse ->
                    promotionDao.insertPromotions(listOf(promotionResponse.toEntity()))
                }
            }
        } catch (e: Exception) {
            Log.d("PromotionRepositoryImpl", "Error fetching from API: ${e.message}")
        }

        val localData = promotionDao.getPromotionById(id).map { entity ->
            if (entity != null) {
                Resource.Success(entity.toDomain())
            } else {
                Resource.Error("Promotion not found")
            }
        }.distinctUntilChanged()

        emitAll(localData)
    }.flowOn(Dispatchers.IO)

    override fun getActivePromotions(): Flow<Resource<List<Promotion>>> = flow {
        emit(Resource.Loading)

        try {
            val response = promotionService.getActivePromotions()
            if (response.isSuccessful) {
                response.body()?.let { promotionResponses ->
                    if (promotionResponses.isNotEmpty()) {
                        val entities = promotionResponses.map { it.toEntity() }
                        promotionDao.insertPromotions(entities)
                    }
                }
            }
        } catch (e: Exception) {
            Log.d("PromotionRepositoryImpl", "Error fetching active promotions: ${e.message}")
        }

        val localData = promotionDao.getActivePromotions().map { entities ->
            val promotions = entities.map { it.toDomain() }
            if (promotions.isEmpty()) {
                Resource.Error("No active promotions found")
            } else {
                Resource.Success(promotions)
            }
        }.distinctUntilChanged()

        emitAll(localData)
    }.flowOn(Dispatchers.IO)

    override fun getActivePromotionsCount(): Flow<Resource<Int>> = flow {
        emit(Resource.Loading)

        try {
            val response = promotionService.getActivePromotionsCount()
            if (response.isSuccessful) {
                response.body()?.let { count ->
                    Log.d("PromotionRepositoryImpl", "Successfully fetched count from API: $count")
                }
            } else {
                Log.w("PromotionRepositoryImpl", "API returned unsuccessful response: ${response.code()}")
            }
        } catch (e: Exception) {
            Log.d("PromotionRepositoryImpl", "Error fetching count from API: ${e.message}")
        }

        val localData = promotionDao.getActivePromotionsCount().map { count ->
            Resource.Success(count)
        }.distinctUntilChanged()

        emitAll(localData)
    }.flowOn(Dispatchers.IO)

    override suspend fun deletePromotion(promotion: Promotion): Resource<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val response = promotionService.deletePromotion(promotion.id)
                if (response.isSuccessful) {
                    promotionDao.deletePromotion(promotion.toEntity())
                    Log.d("PromotionRepositoryImpl", "Promotion deleted successfully from server and local")
                    Resource.Success(Unit)
                } else {
                    Log.w("PromotionRepositoryImpl", "Failed to delete from server: ${response.message()}, deleting locally")
                    promotionDao.deletePromotion(promotion.toEntity())
                    Resource.Error("Deleted locally but failed to sync with server")
                }
            } catch (e: Exception) {
                Log.e("PromotionRepositoryImpl", "Network error deleting promotion: ${e.message}, deleting locally")
                try {
                    promotionDao.deletePromotion(promotion.toEntity())
                    Resource.Error("Deleted locally but network error occurred")
                } catch (localError: Exception) {
                    Log.e("PromotionRepositoryImpl", "Failed to delete locally: ${localError.message}")
                    Resource.Error("Failed to delete promotion: ${localError.message}")
                }
            }
        }

    }

    override suspend fun updatePromotionStatus(promotionId: Int, isActive: Boolean): Resource<Promotion> {
        return try {
            val request = PromotionStatus(active = isActive)
            val response = promotionService.updatePromotionStatus(promotionId, request)

            if (response.isSuccessful && response.body() != null){
                val promotionResponse = response.body()!!
                val promotion = promotionResponse.toDomain()

                promotionDao.updatePromotionStatus(promotionResponse.id, promotionResponse.active)
                Resource.Success(promotion)
            } else {
                val errorBody = response.errorBody()?.string()
                Log.d("PromotionRepositoryImpl", "Error al actualizar estado: ${response.code()} - $errorBody")
                Resource.Error(errorBody?: "Error al actualizar estado")
            }


        } catch(e: Exception){
            Log.d("PromotionRepositoryImpl", "Exception updating state: ${e.message}")
            Resource.Error(e.message ?: "Error de conexion")
        }
    }
}

