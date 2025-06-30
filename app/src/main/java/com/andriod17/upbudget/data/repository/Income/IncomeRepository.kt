package com.andriod17.upbudget.data.repository.Income

import com.andriod17.upbudget.data.model.Income.Income
import com.andriod17.upbudget.helpers.Resource
import kotlinx.coroutines.flow.Flow

interface IncomeRepository {

    fun getIncomes(): Flow<Resource<List<Income>>>
    suspend fun insertIncome(income: Income): Resource<Income>
    fun getIncomeById(id: Int): Flow<Resource<Income?>>
    suspend fun deleteIncome(income: Income): Resource<Unit>
    fun getIncomesByMonth(
        month: String,
        year: String
    ): Flow<Resource<List<Income>>>
    fun sumIncomesByMonth(
        month:String,
        year:String
    ): Flow<Resource<Double>>
}