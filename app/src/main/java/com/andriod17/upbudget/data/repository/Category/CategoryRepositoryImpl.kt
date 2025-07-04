package com.andriod17.upbudget.data.repository.Category

import com.andriod17.upbudget.data.database.dao.CategoryDao
import com.andriod17.upbudget.data.model.Category.Category
import com.andriod17.upbudget.data.remote.category.CategoryService
import com.andriod17.upbudget.data.database.entities.toEntity
import com.andriod17.upbudget.data.database.entities.toDomain
import com.andriod17.upbudget.data.remote.responses.toDomain
import com.andriod17.upbudget.helpers.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class CategoryRepositoryImpl(
    private val categoryService: CategoryService,
    private val categoryDao: CategoryDao
): CategoryRepository {

    override fun getCategories(): Flow<Resource<List<Category>>> = flow {
        emit(Resource.Loading)

        try {
            val response = categoryService.getCategories()
            if (response.isSuccessful) {
                response.body()?.let { remoteList ->
                    categoryDao.deleteAllCategories()
                    categoryDao.insertCategories(remoteList.map { it.toEntity() })
                }
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error"))
        }

        emitAll(
            categoryDao.getAllCategories("").map { entities ->
                Resource.Success(entities.map { it.toDomain() })
            }
        )
    }

    override fun getCategoryById(id: Int): Flow<Resource<Category?>> {
        return categoryDao.getCategoryById(id).map { entity ->
            Resource.Success(entity?.toDomain())
        }
    }

    override suspend fun insertCategory(category: Category): Resource<Category> {
        return try {
            val response = categoryService.insertCategory(category)
            if (response.isSuccessful) {
                response.body()?.let {
                    categoryDao.insertCategory(it.toEntity())
                    Resource.Success(it.toDomain())
                } ?: Resource.Error("Error de respuesta")
            } else {
                Resource.Error("Falló la inserción: ${response.message()}")
            }
        } catch (e: Exception) {
            Resource.Error("Error al insertar: ${e.message}")
        }
    }
}