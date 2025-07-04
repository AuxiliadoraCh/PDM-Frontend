package com.andriod17.upbudget

import android.app.Application
import com.andriod17.upbudget.data.AppProvider

class UpBudgetApp : Application() {
    val appProvider by lazy {
        AppProvider(this)
    }

    companion object {
        private lateinit var instance: UpBudgetApp

        fun getInstance(): UpBudgetApp {
            if (!::instance.isInitialized) {
                throw IllegalStateException("UpBudgetApp instance is not initialized")
            }
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
