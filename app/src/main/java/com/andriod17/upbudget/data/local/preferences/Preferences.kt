package com.andriod17.upbudget.data.local.preferences

import android.content.Context
import com.andriod17.upbudget.ui.navigation.HomeNavigation
import com.andriod17.upbudget.ui.navigation.OnboardingInfoNavigation
import com.andriod17.upbudget.ui.navigation.RegisterNavigation

object Preferences {
    private const val PREFS_NAME = "app_preferences"
    private const val KEY_FIRST_TIME = "first_time_launch"
    private const val KEY_USER_LOGGED_IN = "user_logged_in"
    private const val KEY_ONBOARDING_COMPLETED = "onboarding_completed"

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

    fun isOnboardingCompleted(context: Context): Boolean {
        return try {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            prefs.getBoolean(KEY_ONBOARDING_COMPLETED, false)
        } catch (e: Exception) {
            false
        }
    }

    fun setOnboardingCompleted(context: Context, completed: Boolean) {
        try {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            prefs.edit().putBoolean(KEY_ONBOARDING_COMPLETED, completed).apply()
        } catch (e: Exception) {
        }
    }

    fun isUserLoggedIn(context: Context): Boolean {
        return try {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            prefs.getBoolean(KEY_USER_LOGGED_IN, false)
        } catch (e: Exception) {
            false
        }
    }

    fun setUserLoggedIn(context: Context, loggedIn: Boolean) {
        try {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            prefs.edit().putBoolean(KEY_USER_LOGGED_IN, loggedIn).apply()
        } catch (e: Exception) {
        }
    }

    fun getStartDestination(context: Context): Any {
        return when {
            isFirstTime(context) -> OnboardingInfoNavigation
            isUserLoggedIn(context) -> HomeNavigation
            isOnboardingCompleted(context) -> RegisterNavigation
            else -> OnboardingInfoNavigation
        }
    }
}