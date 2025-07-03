package com.andriod17.upbudget.data.remote.payment

import com.andriod17.upbudget.data.model.PaymentMethod.PaymentMethod
import com.andriod17.upbudget.data.remote.responses.PaymentMethodResponse
import retrofit2.Response
import retrofit2.http.*

interface PaymentMethodService {

    @GET("payment")
    suspend fun getAllPaymentMethods(): Response<List<PaymentMethodResponse>>

    @GET("payment/user")
    suspend fun getUserPaymentMethods(): Response<List<PaymentMethodResponse>>

    @POST("payment")
    suspend fun addPaymentMethod(
        @Body paymentMethod: PaymentMethod
    ): Response<PaymentMethodResponse>

    @DELETE("payment/{id}")
    suspend fun deletePaymentMethod(
        @Path("id") id: Int
    ): Response<Unit>
}






