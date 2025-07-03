package com.andriod17.upbudget.data.local.preferences

import android.content.Context
import com.andriod17.upbudget.ui.navigation.OnboardingInfoNavigation
import com.andriod17.upbudget.ui.navigation.RegisterNavigation

object Preferences {
    private const val PREFS_NAME = "app_preferences"
    private const val KEY_FIRST_TIME = "first_time_launch"

    fun isFirstTime(context: Context): Boolean {
        return try {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            prefs.getBoolean(KEY_FIRST_TIME, true)
        } catch (e: Exception) {
            true
        }
    }

    fun setFirstTime(context: Context, isFirstTime: Boolean) {
        try {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            prefs.edit().putBoolean(KEY_FIRST_TIME, isFirstTime).apply()
        } catch (e: Exception) {
        }
    }

    fun getStartDestination(context: Context): Any {
        return if (isFirstTime(context)) {
            OnboardingInfoNavigation
        } else {
            RegisterNavigation
        }
    }
}