package com.andriod17.upbudget.data.remote.category

import com.andriod17.upbudget.data.model.Category.Category
import com.andriod17.upbudget.data.remote.responses.CategoryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.*

interface CategoryService {
    @GET("categories")
    suspend fun getCategories(): Response<List<CategoryResponse>>
    @GET("categories/{id}")
    suspend fun getCategoryById(@Path("id") id: Int): Response<CategoryResponse>
    @POST("categories")
    suspend fun insertCategory(@Body category: Category): Response<CategoryResponse>
}



