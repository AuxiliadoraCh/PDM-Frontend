package com.andriod17.upbudget.data.repository.Used_Coupons

import android.util.Log
import com.andriod17.upbudget.data.database.dao.UsedCouponDao
import com.andriod17.upbudget.data.database.entities.toDomain
import com.andriod17.upbudget.data.model.Used_Coupons.Requests.UsedCouponRequest
import com.andriod17.upbudget.data.model.Used_Coupons.UsedCoupon
import com.andriod17.upbudget.data.remote.responses.toDomain
import com.andriod17.upbudget.data.remote.responses.toEntity
import com.andriod17.upbudget.data.remote.used_coupon.UsedCouponService
import com.andriod17.upbudget.helpers.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class UsedCouponRepositoryImpl (
    private val usedCouponService: UsedCouponService,
    private val usedCouponDao: UsedCouponDao
    // private val authManager: AuthManager
): UsedCouponRepository {
    private val tempUserId = "temp_user_id"

    override fun getUsedCoupons(user_id: String): Flow<Resource<List<UsedCoupon>>> = flow {
        emit(Resource.Loading)
        try {
            val remoteCoupons = usedCouponService.getUsedCoupons()
            if (remoteCoupons.isSuccessful){
                remoteCoupons.body()?.let { coupons ->
                    if (coupons.isNotEmpty()){
                        usedCouponDao.insertUsedCoupons(coupons.map{it.toEntity()})
                    }
                }
            }
        } catch (e: Exception){
            Log.d("UsedCouponRepository", "Error fetching used coupons: ${e.message}")
        }

         val localCoupons = usedCouponDao.getUsedCoupons(user_id).map { entities ->
             val coupons = entities.map { it.toDomain() }
             if (coupons.isEmpty()){
                 Resource.Success(emptyList())
             } else {
                 Resource.Success(coupons)
             }
         }.distinctUntilChanged()

        emitAll(localCoupons)
    }.flowOn(Dispatchers.IO)

    override suspend fun registerCouponUsage(user_id: String, promotion_id: Int): Resource<UsedCoupon> {
        return try {
            val request = UsedCouponRequest(promotion_id = promotion_id)

            val response = usedCouponService.registerCouponUsage(request)

            if (response.isSuccessful && response.body() != null) {
                val couponResponse = response.body()!!
                val coupon = couponResponse.toDomain()

                usedCouponDao.insertUsedCoupon(couponResponse.toEntity())

                Resource.Success(coupon)
            } else {
                val errorBody = response.errorBody()?.string()
                Log.d("UsedCouponRepository", "Error registering coupon: $errorBody")
                Resource.Error(errorBody ?: "Error al registrar cupón")
            }
        } catch (e: Exception) {
            Log.d("UsedCouponRepository", "Exception registering coupon: ${e.message}")
            Resource.Error(e.message ?: "Error de conexión")
        }
    }


    }
