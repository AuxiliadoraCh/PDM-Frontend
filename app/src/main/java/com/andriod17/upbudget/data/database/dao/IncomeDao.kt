package com.andriod17.upbudget.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.andriod17.upbudget.data.database.entities.IncomeEntity
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface IncomeDao {

    @Query("SELECT * FROM incomes")
    fun getAllIncome(): Flow<List<IncomeEntity>>

    //Para insertar un income
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIncome(income: IncomeEntity)

    //Para traer todos los incomes
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIncomes(incomes: List<IncomeEntity>)

    @Query("SELECT * FROM incomes WHERE id = :id")
    fun getIncomeById(id: Int): Flow<IncomeEntity?>

    @Delete
    suspend fun deleteIncome(income: IncomeEntity)

    @Query("DELETE FROM incomes WHERE id = :id")
    suspend fun deleteIncomeById(id: Int): Int

    @Query("""
    SELECT * FROM incomes 
    WHERE user_id = :user_id
    AND strftime('%Y', datetime(date/1000, 'unixepoch')) = :year
    AND strftime('%m', datetime(date/1000, 'unixepoch')) = :month
    ORDER BY date DESC
""")
    fun getIncomesByMonth(
        user_id: String,
        month: String, // "01", "02", "03", etc.
        year: String   // "2023", "2024", etc.
    ): Flow<List<IncomeEntity>>

    @Query("""
        SELECT SUM(amount) FROM incomes 
        WHERE user_id = :user_id
        AND strftime('%Y', datetime(date/1000, 'unixepoch')) = :year
        AND strftime('%m', datetime(date/1000, 'unixepoch')) = :month
    """)
    suspend fun sumIncomesByMonth(
        user_id: String,
        month: String,
        year: String
    ): Double?
}