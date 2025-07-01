package com.andriod17.upbudget.data.database
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.andriod17.upbudget.data.database.entities.UsedCouponEntity

@Database(
    entities = [UsedCouponEntity::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase: RoomDatabase() {
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
