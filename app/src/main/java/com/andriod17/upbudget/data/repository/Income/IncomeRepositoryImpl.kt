package com.andriod17.upbudget.data.repository.Income

import android.util.Log
import com.andriod17.upbudget.data.database.dao.IncomeDao
import com.andriod17.upbudget.data.database.entities.toDomain
import com.andriod17.upbudget.data.database.entities.toEntity
import com.andriod17.upbudget.data.database.entities.toRequest
import com.andriod17.upbudget.data.model.Income.Income
import com.andriod17.upbudget.data.model.Income.Requests.MonthRequest
import com.andriod17.upbudget.data.remote.income.IncomeService
import com.andriod17.upbudget.data.remote.responses.toDomain
import com.andriod17.upbudget.data.remote.responses.toEntity
import com.andriod17.upbudget.helpers.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class IncomeRepositoryImpl(
    private val incomeService: IncomeService,
    private val incomeDao: IncomeDao
    // private val authManager: AuthManager
): IncomeRepository {
    // Reemplazar
    // authManager.getCurrentUserId()
    private val tempUserId = "temp_user_id"
    private var lastSyncTime = 0L
    private val syncInterval = 5 * 60 * 1000L

    override fun getIncomes(): Flow<Resource<List<Income>>> = flow {
        emit(Resource.Loading)
        try {
            val remoteIncomes = incomeService.getIncomes()
            if (remoteIncomes.isSuccessful){
                remoteIncomes.body()?.let { incomes ->
                    if (incomes.isNotEmpty()){
                        incomeDao.insertIncomes(incomes.map {it.toEntity()})
                    }
                }
            }
        } catch(e: Exception){
            Log.d("Income repository", "Error fetching incomes: ${e.message}")
        }

        // val userId = authManager.getCurrentUserId()
        val userId = tempUserId

        val localIncomes = incomeDao.getAllIncome().map { entities ->
            val incomes = entities.filter{it.user_id == userId }.map {it.toDomain()}
            if (incomes.isEmpty()){
                Resource.Error("No incomes found")
            } else {
                Resource.Success(incomes)
            }
        }.distinctUntilChanged()

        emitAll(localIncomes)
    }.flowOn(Dispatchers.IO)

    override suspend fun insertIncome(income: Income): Resource<Income> {
        return withContext(Dispatchers.IO) {
            val incomeWithUserId = income.copy(user_id = tempUserId)

            val savedIncome = try {
                incomeDao.insertIncome(incomeWithUserId.toEntity())
                incomeWithUserId
            } catch (e: Exception) {
                return@withContext Resource.Error("Failed to save locally: ${e.message}")
            }

            try {
                val request = incomeWithUserId.toRequest()
                val response = incomeService.insertIncome(request)

                if (response.isSuccessful) {
                    response.body()?.let { createdIncome ->
                        incomeDao.insertIncome(createdIncome.toEntity())
                        Log.d("IncomeRepositoryImpl", "Income synced with server successfully")
                        return@withContext Resource.Success(createdIncome.toDomain())
                    }
                } else {
                    Log.w("IncomeRepositoryImpl", "Failed to sync with server: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("IncomeRepositoryImpl", "Network error, but saved locally: ${e.message}")
            }

            Resource.Success(savedIncome)
        }
    }

    private suspend fun syncIfNeeded() {
        val now = System.currentTimeMillis()
        if (now - lastSyncTime > syncInterval) {
            try {
                getIncomes().collect { /* ignoras el resultado */ }
                lastSyncTime = now
            } catch (e: Exception) {
                // Ignorar errores de sync
            }
        }
    }


    override fun getIncomeById(id: Int): Flow<Resource<Income?>> = flow {
        emit(Resource.Loading)

        syncIfNeeded()

        try {
            val response = incomeService.getIncomeBYId(id)
            if (response.isSuccessful) {
                response.body()?.let { incomeResponse ->
                    incomeDao.insertIncome(incomeResponse.toEntity())
                }
            }
        } catch (e: Exception) {
            Log.d("IncomeRepositoryImpl", "Error fetching income from API: ${e.message}")
        }

        val localData = incomeDao.getIncomeById(id).map { entity ->
            if (entity != null) {
                Resource.Success(entity.toDomain())
            } else {
                Resource.Error("Income not found")
            }
        }.distinctUntilChanged()

        emitAll(localData)
    }.flowOn(Dispatchers.IO)

    override suspend fun deleteIncome(income: Income): Resource<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val response = incomeService.deleteIncome(income.id)
                if (response.isSuccessful) {
                    incomeDao.deleteIncome(income.toEntity())
                    Log.d("IncomeRepositoryImpl", "Income deleted successfully from server and local")
                    Resource.Success(Unit)
                } else {
                    Log.w("IncomeRepositoryImpl", "Failed to delete from server: ${response.message()}, deleting locally")
                    incomeDao.deleteIncome(income.toEntity())
                    Resource.Error("Deleted locally but failed to sync with server")
                }
            } catch (e: Exception) {
                Log.e("IncomeRepositoryImpl", "Network error deleting income: ${e.message}, deleting locally")
                try {
                    incomeDao.deleteIncome(income.toEntity())
                    Resource.Error("Deleted locally but network error occurred")
                } catch (localError: Exception) {
                    Log.e("IncomeRepositoryImpl", "Failed to delete locally: ${localError.message}")
                    Resource.Error("Failed to delete income: ${localError.message}")
                }
            }
        }
    }

    override fun getIncomesByMonth(month: String, year: String): Flow<Resource<List<Income>>> = flow {
        emit(Resource.Loading)

        syncIfNeeded()

        try {
            val monthRequest = MonthRequest(month = month, year = year)
            val response = incomeService.getIncomesByMonth(monthRequest)
            if (response.isSuccessful) {
                response.body()?.let { incomeResponses ->
                    if (incomeResponses.isNotEmpty()) {
                        val entities = incomeResponses.map { it.toEntity() }
                        incomeDao.insertIncomes(entities)
                    }
                }
            }
        } catch (e: Exception) {
            Log.d("IncomeRepositoryImpl", "Error fetching incomes by month from API: ${e.message}")
        }

        // TODO: Obtener user_id del authManager
        // val userId = authManager.getCurrentUserId()
        val userId = tempUserId

        val localData = incomeDao.getIncomesByMonth(userId, month, year).map { entities ->
            val incomes = entities.map { it.toDomain() }
            if (incomes.isEmpty()) {
                Resource.Error("No incomes found for this month")
            } else {
                Resource.Success(incomes)
            }
        }.distinctUntilChanged()

        emitAll(localData)
    }.flowOn(Dispatchers.IO)

    override fun sumIncomesByMonth(month: String, year: String): Flow<Resource<Double>> = flow {
        emit(Resource.Loading)

        syncIfNeeded()

        try {
            val monthRequest = MonthRequest(month = month, year = year)
            val response = incomeService.getSumIncomesByMonth(monthRequest)
            if (response.isSuccessful) {
                response.body()?.let { sum ->
                    Log.d("IncomeRepositoryImpl", "Successfully fetched sum from API: $sum")
                    emit(Resource.Success(sum))
                    return@flow
                }
            } else {
                Log.w("IncomeRepositoryImpl", "API returned unsuccessful response: ${response.code()}")
            }
        } catch (e: Exception) {
            Log.d("IncomeRepositoryImpl", "Error fetching sum from API: ${e.message}")
        }

        // TODO: Obtener user_id del authManager
        // val userId = authManager.getCurrentUserId()
        val userId = tempUserId

        val localData = flow {
            val sum = incomeDao.sumIncomesByMonth(userId, month, year) ?: 0.0
            emit(Resource.Success(sum))
        }.distinctUntilChanged()

        emitAll(localData)
    }.flowOn(Dispatchers.IO)

}





