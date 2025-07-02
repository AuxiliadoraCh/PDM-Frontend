package com.andriod17.upbudget.data.repository.Budgets

import android.util.Log
import androidx.compose.ui.graphics.RectangleShape
import com.andriod17.upbudget.data.database.dao.BudgetsDao
import com.andriod17.upbudget.data.database.entities.toDomain
import com.andriod17.upbudget.data.database.entities.toEntity
import com.andriod17.upbudget.data.database.entities.toRequest
import com.andriod17.upbudget.data.model.Budgets.Budgets
import com.andriod17.upbudget.data.model.Budgets.Request.BudgetRequest
import com.andriod17.upbudget.data.remote.budgets.BudgetsService
import com.andriod17.upbudget.data.remote.responses.toDomain
import com.andriod17.upbudget.data.remote.responses.toEntity
import com.andriod17.upbudget.helpers.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class BudgetsRepositoryImpl (
    private val budgetsService: BudgetsService,
    private val budgetsDao: BudgetsDao

) : BudgetsRepository {
    override fun getBudgetsByUser(): Flow<Resource<List<Budgets>>> = flow {
        emit(Resource.Loading)
        try {
            val remoteBudgets = budgetsService.getBudgetsByUser()
            if (remoteBudgets.isSuccessful){
                remoteBudgets.body()?.let { budgets ->
                    if (budgets.isNotEmpty()){
                        budgetsDao.insertBudgets(budgets.map { it.toEntity() })
                    }
                }
            }
        } catch (e: Exception){
            Log.d("Budgets RepositoryImpl", "Error fetching budgets: ${e.message}")
        }

        val localBudgets = budgetsDao.getBudgetsByUser().map { entities ->
            val budgets = entities.map { it.toDomain() }
            if (budgets.isEmpty()){
                Resource.Error("no budgets found")
            } else {
                Resource.Success(budgets)
            }
        }.distinctUntilChanged()

        emitAll(localBudgets)
    }.flowOn(Dispatchers.IO)

    override suspend fun createBudget(budgets: Budgets): Resource<Budgets> {
        return withContext(Dispatchers.IO) {
            val savedBudget = try {
                budgetsDao.createBudget(budgets.toEntity())
            } catch (e: Exception) {
                return@withContext Resource.Error("Failed to save budget locally: ${e.message}")
            }
            try {
                val request = budgets.toRequest()
                val response = budgetsService.createBudget(request)
                if (response.isSuccessful){
                    response.body()?.let { createdBudget ->
                        budgetsDao.createBudget(createdBudget.toEntity())
                        Log.d("BudgetsRepoImpl", "Budget created successfully")
                        return@withContext Resource.Success(createdBudget.toDomain())
                    }
                } else {
                    Log.w("BudgetsRepoImpl", "Failed to sync with server: ${response.message()}")
                }
            } catch(e: Exception){
                Log.e("BudgetRepositoryImpl", "Network error, but saved locally: ${e.message}")
            }

            Resource.Success(budgets)
        }
    }

    override suspend fun updateBudget(budgets: Budgets): Resource<Budgets> {
        return withContext(Dispatchers.IO) {
            try {
                val updatedRows = budgetsDao.updateBudget(budgets.toEntity())
                if (updatedRows == 0) {
                    return@withContext Resource.Error("No se pudo actualizar localmente")
                }

                val request = budgets.toRequest()
                val response = budgetsService.updateBudget(budgets.id, request)

                if (response.isSuccessful) {
                    response.body()?.let { updated ->
                        budgetsDao.updateBudget(updated.toEntity())
                        return@withContext Resource.Success(updated.toDomain())
                    }
                } else {
                    return@withContext Resource.Error("Error remoto: ${response.message()}")
                }

            } catch (e: Exception) {
                Log.e("BudgetsRepoImpl", "Error en updateBudget: ${e.message}")
                return@withContext Resource.Error("Error en la actualización: ${e.message}")
            }

            Resource.Success(budgets)
        }
    }

    override suspend fun deleteBudgetById(id: Int): Resource<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                budgetsDao.deleteBudgetById(id)

                val response = budgetsService.deleteBudgetById(id)
                if (response.isSuccessful) {
                    return@withContext Resource.Success(Unit)
                } else {
                    return@withContext Resource.Error("Error al borrar en servidor: ${response.message()}")
                }

            } catch (e: Exception) {
                Log.e("BudgetsRepoImpl", "Error en deleteBudgetById: ${e.message}")
                return@withContext Resource.Error("Error al borrar: ${e.message}")
            }
        }
    }

    override fun getBudgetById(id: Int): Flow<Resource<Budgets>> {
        return budgetsDao.getBudgetById(id)
            .map { entity ->
                entity?.let {
                    Resource.Success(it.toDomain())
                } ?: Resource.Error("Presupuesto no encontrado")
            }
            .catch { e ->
                emit(Resource.Error("Error al obtener presupuesto: ${e.message}"))
            }
            .flowOn(Dispatchers.IO)
    }

}