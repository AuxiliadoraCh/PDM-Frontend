package com.andriod17.upbudget.data.remote

import android.content.Context
import com.andriod17.upbudget.UpBudgetApp
import com.andriod17.upbudget.data.remote.services.AuthService
import com.andriod17.upbudget.data.local.SessionManager
import com.andriod17.upbudget.data.remote.interceptors.AuthInterceptor
import com.andriod17.upbudget.data.remote.promotion.PromotionService
import com.andriod17.upbudget.data.remote.used_coupon.UsedCouponService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://upbudget-6le4.onrender.com/api/"

    private fun createClient(sessionManager: SessionManager): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(AuthInterceptor(sessionManager))
            .build()
    }


    private fun createRetrofit(sessionManager: SessionManager): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(createClient(sessionManager))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val authService: AuthService by lazy {
        val application = UpBudgetApp.getInstance()
        val context: Context =  application.applicationContext
        val sessionManager = SessionManager.getInstance(context)
        createRetrofit(sessionManager).create(AuthService::class.java)
    }

    val promotionService: PromotionService by lazy {
        val application = UpBudgetApp.getInstance()
        val context: Context = application.applicationContext
        val sessionManager = SessionManager.getInstance(context)
        createRetrofit(sessionManager).create(PromotionService::class.java)
    }

    val usedcouponService: UsedCouponService by lazy {
        val application = UpBudgetApp.getInstance()
        val context: Context = application.applicationContext
        val sessionManager = SessionManager.getInstance(context)
        createRetrofit(sessionManager).create(UsedCouponService::class.java)
    }
}


