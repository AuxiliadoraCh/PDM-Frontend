package com.andriod17.upbudget.data.database

import com.andriod17.upbudget.data.remote.educationalContent.EducationalContentService
import com.andriod17.upbudget.data.remote.budgets.BudgetsService
import com.andriod17.upbudget.data.remote.expense.ExpenseService
import com.andriod17.upbudget.data.remote.promotion.PromotionService
import com.andriod17.upbudget.data.remote.used_coupon.UsedCouponService
import com.andriod17.upbudget.data.remote.services.AuthService
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

    val budgetsService: BudgetsService by lazy {
        retrofit.create(BudgetsService::class.java)
    }

    val promotionService: PromotionService by lazy {
        retrofit.create(PromotionService::class.java)
    }

    val usedcouponService: UsedCouponService by lazy {
        retrofit.create(UsedCouponService::class.java)
    }

    val expenseService: ExpenseService by lazy {
        retrofit.create(ExpenseService::class.java)
    }
    val authService: AuthService by lazy {
        retrofit.create(AuthService::class.java)
    }
    val educationalContentService: EducationalContentService by lazy {
        retrofit.create(EducationalContentService::class.java)
    }
}