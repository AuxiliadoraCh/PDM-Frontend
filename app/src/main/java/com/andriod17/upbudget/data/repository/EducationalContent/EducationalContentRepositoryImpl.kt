package com.andriod17.upbudget.data.repository.EducationalContent

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.andriod17.upbudget.data.database.dao.EducationalContentDao
import com.andriod17.upbudget.data.database.entities.toDomain
import com.andriod17.upbudget.data.model.EducationalContent.EducationalContent
import com.andriod17.upbudget.data.model.EducationalContent.toRequest
import com.andriod17.upbudget.data.remote.educationalContent.EducationalContentService
import com.andriod17.upbudget.data.remote.responses.toDomain
import com.andriod17.upbudget.data.remote.responses.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class EducationalContentRepositoryImpl(
    private val educationalContentDao: EducationalContentDao,
    private val educationalContentService: EducationalContentService
) : EducationalContentRepository {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getAllContents(
    ): Flow<List<EducationalContent>> = flow {
        try {
            val localContents = educationalContentDao.getAll().first()
            emit(localContents.map { it.toDomain() })
        } catch (e: Exception) {
            emit(emptyList())
        }

        try {
            val remoteContents = educationalContentService.getEducationalContents()
            remoteContents.forEach {
                educationalContentDao.insertOrUpdate(it.toEntity())
            }
            emit(remoteContents.map { it.toDomain() })
        } catch (e: Exception) {
            Log.e("Repo", "Error al sincronizar con Supabase: ${e.message}")
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun createEducationalContent(content: EducationalContent): EducationalContent {
        return try {
            val request = content.toRequest()
            val response = educationalContentService.createEducationalContent(request)
            educationalContentDao.insertOrUpdate(response.toEntity())
            response.toDomain()
        } catch (e: HttpException) {
            throw Exception("Error al crear el contenido en Supabase: ${e.message()}")
        } catch (e: Exception) {
            throw Exception("Error en la creación del contenido: ${e.message}")
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun updateEducationalContent(content: EducationalContent): EducationalContent {
        return try {
            val response = educationalContentService.createEducationalContent(content.toRequest())  // Usamos el método de creación, podrías agregar uno de actualización si fuera necesario
            educationalContentDao.insertOrUpdate(response.toEntity())
            response.toDomain()
        } catch (e: HttpException) {
            throw Exception("Error al actualizar el contenido en Supabase: ${e.message()}")
        } catch (e: Exception) {
            throw Exception("Error en la actualización del contenido: ${e.message}")
        }
    }

    override suspend fun deleteEducationalContent(id: Long) {
        try {
            educationalContentService.deleteEducationalContent(id)
            val content = educationalContentDao.getById(id)
            content?.let {
                educationalContentDao.delete(it)
            }
        } catch (e: HttpException) {
            throw Exception("Error al eliminar el contenido en Supabase: ${e.message()}")
        } catch (e: Exception) {
            throw Exception("Error en la eliminación del contenido: ${e.message}")
        }
    }
}
