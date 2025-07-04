package com.andriod17.upbudget

import android.app.Application

class MyApplication : Application() {
    companion object {
        private lateinit var instance: MyApplication

        fun getInstance(): MyApplication {
            if (!::instance.isInitialized) {
                throw IllegalStateException("MyApplication instance is not initialized")
            }
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}