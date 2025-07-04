package com.andriod17.upbudget.data.repository.EducationalContent

import com.andriod17.upbudget.data.model.EducationalContent.EducationalContent
import kotlinx.coroutines.flow.Flow

interface EducationalContentRepository {

    fun getAllContents(): Flow<List<EducationalContent>>

    suspend fun createEducationalContent(content: EducationalContent): EducationalContent

    suspend fun updateEducationalContent(content: EducationalContent): EducationalContent

    suspend fun deleteEducationalContent(id: Long)
}
