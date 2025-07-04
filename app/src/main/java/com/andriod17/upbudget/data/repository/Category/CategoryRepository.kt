package com.andriod17.upbudget.data.repository.Category

import com.andriod17.upbudget.data.model.Category.Category
import com.andriod17.upbudget.helpers.Resource
import kotlinx.coroutines.flow.Flow

interface CategoryRepository{
    fun getCategories(): Flow<Resource<List<Category>>>
    fun getCategoryById(id: Int): Flow<Resource<Category?>>
    suspend fun insertCategory(category: Category): Resource<Category>
}
