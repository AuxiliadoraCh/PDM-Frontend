package com.andriod17.upbudget.data.remote

import com.andriod17.upbudget.data.remote.educationalContent.EducationalContentService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://your-project-id.supabase.co/rest/v1/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val educationalContentService: EducationalContentService = retrofit.create(EducationalContentService::class.java)
}
