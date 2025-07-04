package com.andriod17.upbudget.data.remote.interceptors

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import com.andriod17.upbudget.data.local.SessionManager

class AuthInterceptor(private val sessionManager: SessionManager) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = sessionManager.getAuthTokenSync()

        Log.d("AuthInterceptor", "Token: '$token'") // Debug temporal

        val request = if (token.isNotEmpty()) {
            chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        } else {
            Log.d("AuthInterceptor", "No token available")
            chain.request()
        }

        return chain.proceed(request)
    }
}
