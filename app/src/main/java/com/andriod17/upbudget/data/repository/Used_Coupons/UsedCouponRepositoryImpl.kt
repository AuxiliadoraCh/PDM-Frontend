package com.andriod17.upbudget.data.repository.Used_Coupons

import android.util.Log
import com.andriod17.upbudget.data.database.dao.UsedCouponDao
import com.andriod17.upbudget.data.database.entities.toDomain
import com.andriod17.upbudget.data.local.SessionManager
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
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class UsedCouponRepositoryImpl (
    private val usedCouponService: UsedCouponService,
    private val usedCouponDao: UsedCouponDao,
    private val sessionManager: SessionManager
): UsedCouponRepository {

    override fun getUsedCoupons(): Flow<Resource<List<UsedCoupon>>> = flow {
        emit(Resource.Loading)

        val userId = sessionManager.getUserIdSync()
        Log.d("UsedCouponRepository", "User ID from session: '$userId'")

        try {
            val remoteCoupons = usedCouponService.getUsedCoupons()
            if (remoteCoupons.isSuccessful) {
                remoteCoupons.body()?.let { coupons ->
                    Log.d("UsedCouponRepository", "Remote coupons received: ${coupons.size}")

                    val userCoupons = coupons.filter { it.user_id == userId }
                    Log.d("UsedCouponRepository", "User coupons filtered: ${userCoupons.size}")

                    if (userCoupons.isNotEmpty()) {
                        usedCouponDao.deleteUserCoupons(userId)
                        usedCouponDao.insertUsedCoupons(userCoupons.map { it.toEntity() })
                        Log.d("UsedCouponRepository", "Updated ${userCoupons.size} coupons in local DB")
                    }
                }
            } else {
                Log.d("UsedCouponRepository", "Remote request failed: ${remoteCoupons.code()}")
            }
        } catch (e: Exception) {
            Log.d("UsedCouponRepository", "Error fetching remote coupons: ${e.message}")
        }

        try {
            val localCoupons = usedCouponDao.getUsedCoupons(userId).first()
            Log.d("UsedCouponRepository", "Local coupons count: ${localCoupons.size}")

            val domainCoupons = localCoupons.map { it.toDomain() }
            emit(Resource.Success(domainCoupons))
        } catch (e: Exception) {
            Log.d("UsedCouponRepository", "Error fetching local coupons: ${e.message}")
            emit(Resource.Error(e.message ?: "Error al cargar cupones"))
        }

    }.flowOn(Dispatchers.IO)

    override suspend fun registerCouponUsage(promotion_id: Int): Resource<UsedCoupon> {
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
