package com.andriod17.upbudget.data
import android.content.Context
import com.andriod17.upbudget.data.database.AppDatabase
import com.andriod17.upbudget.data.database.dao.PromotionDao
import com.andriod17.upbudget.data.remote.RetrofitInstance
import com.andriod17.upbudget.data.remote.services.AuthService
import com.andriod17.upbudget.data.remote.used_coupon.UsedCouponService
import com.andriod17.upbudget.data.repository.Promotion.PromotionRepository
import com.andriod17.upbudget.data.repository.Promotion.PromotionRepositoryImpl
import com.andriod17.upbudget.data.repository.Used_Coupons.UsedCouponRepository
import com.andriod17.upbudget.data.repository.Used_Coupons.UsedCouponRepositoryImpl

class AppProvider(context: Context) {
    private val appDatabase = AppDatabase.getDatabase(context)
    private val promotionDao = appDatabase.promotionDao()
    private val promotionService = RetrofitInstance.promotionService
    private val promotionRepository = PromotionRepositoryImpl(promotionService, promotionDao)
    private val usedCouponService = RetrofitInstance.usedcouponService
    private val usedCouponDao = appDatabase.usedCouponDao()
    private val usedCouponRepository = UsedCouponRepositoryImpl(usedCouponService,usedCouponDao)


    fun providePromotionRepository(): PromotionRepository {
        return promotionRepository
    }

    fun provideUsedCouponRepository(): UsedCouponRepository {
        return usedCouponRepository
    }

}
