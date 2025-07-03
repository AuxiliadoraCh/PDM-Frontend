package com.andriod17.upbudget.data.remote.educationalContent

import com.andriod17.upbudget.data.model.EducationalContent.Request.EducationalContentRequest
import com.andriod17.upbudget.data.remote.responses.EducationalContentResponse
import retrofit2.http.*

interface EducationalContentService {

    @GET("educational_content")
    suspend fun getEducationalContents(): List<EducationalContentResponse> // Devolvemos directamente la lista

    @POST("educational_content")
    suspend fun createEducationalContent(@Body request: EducationalContentRequest): EducationalContentResponse

    @DELETE("educational_content/{id}")
    suspend fun deleteEducationalContent(@Path("id") id: Long): Unit // No necesitas envolver la respuesta en `Response<Unit>`
}
