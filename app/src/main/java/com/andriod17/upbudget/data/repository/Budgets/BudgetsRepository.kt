package com.andriod17.upbudget.data.repository.Budgets

import com.andriod17.upbudget.data.database.entities.BudgetsEntity
import com.andriod17.upbudget.data.model.Budgets.Budgets
import com.andriod17.upbudget.helpers.Resource
import kotlinx.coroutines.flow.Flow

interface BudgetsRepository {

    fun getBudgetsByUser(): Flow<Resource<List<Budgets>>>

    suspend fun createBudget(budgets: Budgets): Resource<Budgets>

    fun getBudgetById(id: Int): Flow<Resource<Budgets>>

    suspend fun updateBudget(budgets: Budgets): Resource<Budgets>

    suspend fun deleteBudgetById(id: Int): Resource<Unit>

}