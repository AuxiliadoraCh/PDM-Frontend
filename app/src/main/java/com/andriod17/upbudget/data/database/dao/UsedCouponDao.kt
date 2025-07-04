package com.andriod17.upbudget.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andriod17.upbudget.data.database.entities.UsedCouponEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UsedCouponDao {

    @Query("SELECT * FROM used_coupons WHERE user_id = :user_id")
    fun getUsedCoupons(user_id: String): Flow<List<UsedCouponEntity>>

    @Query("SELECT * FROM used_coupons WHERE user_id = :user_id AND promotion_id = :promotion_id LIMIT 1")
    fun checkUsedCoupon(user_id: String, promotion_id: Int): Flow<UsedCouponEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsedCoupon(usedCoupon : UsedCouponEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsedCoupons(usedCoupons : List<UsedCouponEntity>)

    @Query("DELETE FROM used_coupons WHERE user_id = :user_id AND promotion_id = :promotion_id")
    suspend fun deleteUsedCoupon(user_id: String, promotion_id: Int)
}