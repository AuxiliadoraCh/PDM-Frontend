package com.andriod17.upbudget.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.andriod17.upbudget.data.database.entities.BudgetsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BudgetsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createBudget(budget: BudgetsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBudgets(budgets: List<BudgetsEntity>)

    @Query("SELECT * FROM budgets")
    fun getBudgetsByUser(): Flow<List<BudgetsEntity>>

    @Query("SELECT * FROM budgets where id = :id")
    fun getBudgetById(id: Int): Flow<BudgetsEntity?>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateBudget(budget: BudgetsEntity): Int

    @Query("DELETE FROM budgets WHERE id = :id")
    suspend fun  deleteBudgetById(id: Int)

}