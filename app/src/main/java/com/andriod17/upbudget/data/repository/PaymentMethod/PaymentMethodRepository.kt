package com.andriod17.upbudget.data.repository.PaymentMethod

import com.andriod17.upbudget.data.model.PaymentMethod.PaymentMethod
import com.andriod17.upbudget.helpers.Resource
import kotlinx.coroutines.flow.Flow

interface PaymentMethodRepository {

    fun getAllPaymentMethods(userId: String): Flow<Resource<List<PaymentMethod>>>

    fun getPaymentMethodById(id: Int): Flow<Resource<PaymentMethod?>>

    suspend fun addPaymentMethod(method: PaymentMethod): Resource<PaymentMethod>

    suspend fun deletePaymentMethod(id: Int): Resource<Unit>
}

