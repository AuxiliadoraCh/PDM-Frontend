package com.andriod17.upbudget.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andriod17.upbudget.data.database.entities.EducationalContentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EducationalContentDao {

    @Query("SELECT * FROM educational_content ORDER BY created_at DESC")
    fun getAll(): Flow<List<EducationalContentEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(content: EducationalContentEntity)

    @Delete
    suspend fun delete(content: EducationalContentEntity)

    @Query("DELETE FROM educational_content")
    suspend fun clearAllContents()

    @Query("SELECT * FROM educational_content WHERE id = :id")
    suspend fun getById(id: Long): EducationalContentEntity?
}
