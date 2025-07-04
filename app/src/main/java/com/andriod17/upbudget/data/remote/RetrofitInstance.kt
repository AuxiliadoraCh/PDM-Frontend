package com.andriod17.upbudget.data.remote

import android.content.Context
import com.andriod17.upbudget.MyApplication
import com.andriod17.upbudget.data.remote.services.AuthService
import com.andriod17.upbudget.data.local.SessionManager
import com.andriod17.upbudget.data.remote.interceptors.AuthInterceptor
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
            .client(createClient(sessionManager))  // Usar el cliente con el AuthInterceptor
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Crear una instancia del servicio AuthService
    val authService: AuthService by lazy {
        val application = MyApplication.getInstance()
        val context: Context =  application.applicationContext
        val sessionManager = SessionManager.getInstance(context)
        createRetrofit(sessionManager).create(AuthService::class.java)
    }
}
