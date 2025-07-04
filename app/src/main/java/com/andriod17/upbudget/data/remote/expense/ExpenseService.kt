package com.andriod17.upbudget.data.remote.expense

import com.andriod17.upbudget.data.model.Expense.Request.ExpenseRequest
import com.andriod17.upbudget.data.remote.responses.ExpenseResponse
import retrofit2.Response
import retrofit2.http.*

interface ExpenseService {

    @POST("expenses")
    suspend fun createExpense(@Body request: ExpenseRequest): Response<ExpenseResponse>


    @GET("expenses/{user_id}")
    suspend fun getExpensesByUser(@Path("user_id") userId: String): Response<List<ExpenseResponse>>


    @PUT("expenses/{id}")
    suspend fun updateExpense(@Path("id") id: Int, @Body fields: Map<String, Any?>): Response<ExpenseResponse>


    @DELETE("expenses/{id}")
    suspend fun deleteExpense(@Path("id") id: Int): Response<Unit>
}

