package com.andriod17.upbudget.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.andriod17.upbudget.data.database.dao.EducationalContentDao
import com.andriod17.upbudget.data.database.entities.EducationalContentEntity
import com.andriod17.upbudget.data.model.EducationalContent.Converters

@Database(entities = [EducationalContentEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun educationalContentDao(): EducationalContentDao
}