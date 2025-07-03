package com.andriod17.upbudget.data.remote

import com.andriod17.upbudget.data.remote.category.CategoryService
import com.andriod17.upbudget.data.remote.income.IncomeService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://upbudget-6le4.onrender.com/api/"

    val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val incomeService: IncomeService by lazy {
        retrofit.create(IncomeService::class.java)
    }
    val categoryService: CategoryService by lazy{
        retrofit.create(CategoryService::class.java)
    }
}