package com.andriod17.upbudget.data.repository.Expense

import com.andriod17.upbudget.data.model.Expense.Expense
import com.andriod17.upbudget.helpers.Resource
import kotlinx.coroutines.flow.Flow

interface ExpenseRepository {
    suspend fun syncExpenses(userId: String): Flow<Resource<List<Expense>>>
    suspend fun createExpense(expense: Expense): Resource<Expense>
    suspend fun updateExpense(id: Int, fields: Map<String, Any?>): Resource<Expense>
    suspend fun deleteExpense(id: Int): Resource<Unit>
}

