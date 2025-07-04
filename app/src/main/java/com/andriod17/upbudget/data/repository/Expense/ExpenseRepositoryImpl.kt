package com.andriod17.upbudget.data.repository.Expense


import com.andriod17.upbudget.data.database.dao.ExpenseDao
import com.andriod17.upbudget.data.database.entities.toDomain
import com.andriod17.upbudget.data.model.Expense.Expense
import com.andriod17.upbudget.data.model.Expense.Request.ExpenseRequest
import com.andriod17.upbudget.data.remote.expense.ExpenseService
import com.andriod17.upbudget.data.remote.responses.toEntity
import com.andriod17.upbudget.helpers.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class ExpenseRepositoryImpl(
    private val expenseDao: ExpenseDao,
    private val expenseService: ExpenseService
) : ExpenseRepository {

    override suspend fun syncExpenses(userId: String): Flow<Resource<List<Expense>>> = flow {
        emit(Resource.Loading)
        try {

            val remoteResponse = expenseService.getExpensesByUser(userId)
            if (remoteResponse.isSuccessful) {
                val expenseResponses = remoteResponse.body() ?: emptyList()
                val expenseEntities = expenseResponses.map { it.toEntity() }
                expenseDao.insertExpenses(expenseEntities)
                val expenses = expenseEntities.map { it.toDomain() }
                emit(Resource.Success(expenses))
            } else {
                emit(Resource.Error("Failed to fetch expenses: ${remoteResponse.message()}"))
            }
        } catch (e: Exception) {

            val localExpenses = expenseDao.getAllExpenses().first()
            val expenses = localExpenses.map { it.toDomain() }
            emit(Resource.Error("Error syncing expenses: ${e.message}"))
            emit(Resource.Success(expenses))
        }
    }

    override suspend fun createExpense(expense: Expense): Resource<Expense> = withContext(Dispatchers.IO) {
        try {
            val request = ExpenseRequest(
                user_id = expense.user_id,
                amount = expense.amount,
                description = expense.description,
                category_id = expense.category_id,
                payment_id = expense.payment_method_id
            )
            val response = expenseService.createExpense(request)
            if (response.isSuccessful) {
                val expenseResponse = response.body()
                if (expenseResponse != null) {
                    val entity = expenseResponse.toEntity()
                    expenseDao.insertExpense(entity)
                    return@withContext Resource.Success(entity.toDomain())
                } else {
                    return@withContext Resource.Error("Empty response from server")
                }
            } else {
                return@withContext Resource.Error("Failed to create expense: ${response.message()}")
            }
        } catch (e: Exception) {
            return@withContext Resource.Error("Error creating expense: ${e.message}")
        }
    }

    override suspend fun updateExpense(id: Int, fields: Map<String, Any?>): Resource<Expense> = withContext(Dispatchers.IO) {
        try {
            val response = expenseService.updateExpense(id, fields)
            if (response.isSuccessful) {
                val expenseResponse = response.body()
                if (expenseResponse != null) {
                    val entity = expenseResponse.toEntity()
                    expenseDao.updateExpense(entity)
                    return@withContext Resource.Success(entity.toDomain())
                } else {
                    return@withContext Resource.Error("Empty response from server")
                }
            } else {
                return@withContext Resource.Error("Failed to update expense: ${response.message()}")
            }
        } catch (e: Exception) {
            return@withContext Resource.Error("Error updating expense: ${e.message}")
        }
    }

    override suspend fun deleteExpense(id: Int): Resource<Unit> = withContext(Dispatchers.IO) {
        try {
            val expenses = expenseDao.getAllExpenses().first()
            val expenseToDelete = expenses.find { it.id == id }
            if (expenseToDelete != null) {
                expenseDao.deleteExpense(expenseToDelete)
            }
            val response = expenseService.deleteExpense(id)
            if (response.isSuccessful) {
                return@withContext Resource.Success(Unit)
            } else {
                return@withContext Resource.Error("Failed to delete expense: ${response.message()}")
            }
        } catch (e: Exception) {
            return@withContext Resource.Error("Error deleting expense: ${e.message}")
        }
    }
}