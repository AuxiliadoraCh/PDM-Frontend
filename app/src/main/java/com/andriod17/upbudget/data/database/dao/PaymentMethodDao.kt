package com.andriod17.upbudget.data.database.dao

import androidx.room.*
import com.andriod17.upbudget.data.database.entities.PaymentMethodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PaymentMethodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPaymentMethod(method: PaymentMethodEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPaymentMethods(methods: List<PaymentMethodEntity>)

    @Query("SELECT * FROM payment_methods WHERE user_id = :userId OR user_id IS NULL ORDER BY is_default DESC")
    fun getAllPaymentMethods(userId: String): Flow<List<PaymentMethodEntity>>

    @Query("SELECT * FROM payment_methods WHERE id = :id")
    fun getPaymentMethodById(id: Int): Flow<PaymentMethodEntity?>

    @Query("DELETE FROM payment_methods")
    suspend fun deleteAllPaymentMethods()

    @Delete
    suspend fun deletePaymentMethod(method: PaymentMethodEntity)
}



