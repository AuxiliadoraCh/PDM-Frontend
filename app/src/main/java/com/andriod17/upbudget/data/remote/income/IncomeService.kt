package com.andriod17.upbudget.data.remote.income

import com.andriod17.upbudget.data.model.Income.Requests.IncomeRequest
import com.andriod17.upbudget.data.model.Income.Requests.MonthRequest
import com.andriod17.upbudget.data.remote.responses.IncomeResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface IncomeService {

    @GET("incomes/")
    suspend fun getIncomes(): Response<List<IncomeResponse>>

    @POST("incomes/")
    suspend fun insertIncome(@Body request: IncomeRequest): Response<IncomeResponse>

    @GET("incomes/{id}")
    suspend fun getIncomeBYId(@Path("id") id: Int): Response<IncomeResponse>

    @POST("incomes/date/")
    suspend fun getIncomesByMonth(@Body request: MonthRequest): Response<List<IncomeResponse>>

    @POST("incomes/sum/")
    suspend fun getSumIncomesByMonth(@Body request: MonthRequest): Response<Double>

    @DELETE("incomes/{id}")
    suspend fun deleteIncome(@Path("id") id: Int): Response<Unit>

}