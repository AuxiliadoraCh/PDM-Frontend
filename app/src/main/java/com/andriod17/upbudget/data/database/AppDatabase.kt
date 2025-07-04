package com.andriod17.upbudget.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.andriod17.upbudget.data.database.entities.BudgetsEntity
import androidx.room.TypeConverters
import com.andriod17.upbudget.data.database.dao.PromotionDao
import com.andriod17.upbudget.data.database.dao.UsedCouponDao
import com.andriod17.upbudget.data.database.entities.UsedCouponEntity
import com.andriod17.upbudget.data.database.entities.Converters
import com.andriod17.upbudget.data.database.entities.PromotionEntity
import com.andriod17.upbudget.data.model.Used_Coupons.UsedCoupon


@Database(
    entities = [PromotionEntity::class, UsedCouponEntity::class, BudgetsEntity::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun promotionDao(): PromotionDao
    abstract fun usedCouponDao(): UsedCouponDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context = context.applicationContext,
                    klass = AppDatabase::class.java,
                    name = "upbudget_database"
                )
                    .fallbackToDestructiveMigration(false)
                    .build()
                    .also {
                        INSTANCE = it
                    }
                instance
            }
        }
    }
}
