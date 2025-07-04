package com.andriod17.upbudget.data.remote.educationalContent

import com.andriod17.upbudget.data.model.EducationalContent.Request.EducationalContentRequest
import com.andriod17.upbudget.data.remote.responses.EducationalContentResponse
import retrofit2.Response
import retrofit2.http.*

interface EducationalContentService {

    @GET("/")
    suspend fun getEducationalContents(): Response<List<EducationalContentResponse>>

    @POST("/")
    suspend fun createEducationalContent(@Body request: EducationalContentRequest): Response<EducationalContentResponse>

    @GET("/{id}")
    suspend fun getSingleEducationalContent(@Path("id") id: Long): Response<EducationalContentResponse>

    @PUT("/{id}")
    suspend fun updateEducationalContent(@Path("id") id: Long, @Body request: EducationalContentRequest): Response<EducationalContentResponse>

    @DELETE("/{id}")
    suspend fun deleteEducationalContent(@Path("id") id: Long): Response<Unit>
}
