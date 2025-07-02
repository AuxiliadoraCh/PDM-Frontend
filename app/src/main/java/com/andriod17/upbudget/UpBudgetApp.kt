package com.andriod17.upbudget

import android.app.Application
import com.andriod17.upbudget.data.AppProvider

class UpBudgetApp: Application(){
    val appProvider by lazy {
        AppProvider(this)
    }
}