package com.andriod17.upbudget.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andriod17.upbudget.data.database.entitites.PromotionEntity
import kotlinx.coroutines.flow.Flow
import androidx.room.Insert


@Dao
interface PromotionDao {

    @Query("SELECT * FROM promotions")
    fun getPromotions(): Flow<List<PromotionEntity>>

    @Query("SELECT * FROM promotions WHERE id = :id")
    fun getPromotionById(id: Int): Flow<PromotionEntity?>

    @Query("SELECT * FROM promotions WHERE active = 1")
    fun getActivePromotions(): Flow<List<PromotionEntity>>

    @Query("SELECT COUNT(*) FROM promotions WHERE active = 1")
    fun getActivePromotionsCount(): Flow<Int>

    @Query("SELECT * FROM promotions WHERE active = 1 AND start_date <= :currentDate AND end_date >= :currentDate")
    fun getCurrentPromotions(currentDate: Long): Flow<List<PromotionEntity>>

    @Delete
    suspend fun deletePromotion(promotion: PromotionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPromotions(promotion: List<PromotionEntity>)

}