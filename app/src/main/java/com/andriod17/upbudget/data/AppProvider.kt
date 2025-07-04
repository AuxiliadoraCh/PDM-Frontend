package com.andriod17.upbudget.data
import android.content.Context
import com.andriod17.upbudget.data.database.AppDatabase
import com.andriod17.upbudget.data.database.dao.PromotionDao
import com.andriod17.upbudget.data.remote.RetrofitInstance
import com.andriod17.upbudget.data.repository.Expense.ExpenseRepository
import com.andriod17.upbudget.data.repository.Expense.ExpenseRepositoryImpl
import com.andriod17.upbudget.data.repository.Promotion.PromotionRepository
import com.andriod17.upbudget.data.repository.Promotion.PromotionRepositoryImpl

class AppProvider(context: Context) {
    private val appDatabase = AppDatabase.getDatabase(context)
    private val promotionDao = appDatabase.promotionDao()
    private val expenseDao = appDatabase.ExpenseDao()
    private val expenseService = RetrofitInstance.expenseService
    private val promotionService = RetrofitInstance.promotionService
    private val promotionRepository = PromotionRepositoryImpl(promotionService, promotionDao)
    private val expenseRepository = ExpenseRepositoryImpl(expenseDao,expenseService)

    fun providePromotionRepository(): PromotionRepository {
        return promotionRepository
    }
    fun provideExpenseRepository(): ExpenseRepository{
        return expenseRepository
    }

}