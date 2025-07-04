package com.andriod17.upbudget.data.remote.budgets

import com.andriod17.upbudget.data.model.Budgets.Request.BudgetRequest
import com.andriod17.upbudget.data.remote.responses.BudgetsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface BudgetsService {

    @POST("budgets/")
    suspend fun createBudget(@Body request: BudgetRequest): Response<BudgetsResponse>

    @GET("budgets/")
    suspend fun getBudgetsByUser(): Response<List<BudgetsResponse>>

    // Revisar backend si la consulta tiene seguridad que solo el usuario que lo creó puede actualizarlo

    @GET("budgets/budget/{id}")
    suspend fun getBudgetById(@Path("id") id: Int): Response<BudgetsResponse?>

    @PUT("budgets/budget/{id}")
    suspend fun updateBudget(@Path("id") id: Int , @Body request: BudgetRequest): Response<BudgetsResponse>

    @DELETE("budgets/budget/{id}")
    suspend fun deleteBudgetById(@Path ("id") id: Int): Response<Unit>

}