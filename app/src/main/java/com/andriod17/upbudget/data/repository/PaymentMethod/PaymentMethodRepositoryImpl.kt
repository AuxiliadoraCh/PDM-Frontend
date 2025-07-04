package com.andriod17.upbudget.data.repository.PaymentMethod


import com.andriod17.upbudget.data.database.dao.PaymentMethodDao
import com.andriod17.upbudget.data.model.PaymentMethod.PaymentMethod
import com.andriod17.upbudget.data.remote.payment.PaymentMethodService
import com.andriod17.upbudget.helpers.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import com.andriod17.upbudget.data.remote.responses.toEntity
import com.andriod17.upbudget.data.remote.responses.toDomain
import com.andriod17.upbudget.data.database.entities.toDomain



class PaymentMethodRepositoryImpl(
    private val paymentService: PaymentMethodService,
    private val paymentDao: PaymentMethodDao
) : PaymentMethodRepository {

    override fun getAllPaymentMethods(userId: String): Flow<Resource<List<PaymentMethod>>> = flow {
        emit(Resource.Loading)

        try {
            val response = paymentService.getUserPaymentMethods()
            if (response.isSuccessful) {
                response.body()?.let { remoteList ->
                    paymentDao.deleteAllPaymentMethods()
                    paymentDao.insertPaymentMethods(remoteList.map { it.toEntity() })
                }
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error"))
        }

        emitAll(
            paymentDao.getAllPaymentMethods(userId).map { entities ->
                Resource.Success(entities.map { it.toDomain() })
            }
        )
    }

    override fun getPaymentMethodById(id: Int): Flow<Resource<PaymentMethod?>> {
        return paymentDao.getPaymentMethodById(id).map { entity ->
            Resource.Success(entity?.toDomain())
        }
    }

    override suspend fun addPaymentMethod(method: PaymentMethod): Resource<PaymentMethod> {
        return try {
            val response = paymentService.addPaymentMethod(method)
            if (response.isSuccessful) {
                response.body()?.let {
                    paymentDao.insertPaymentMethod(it.toEntity())
                    Resource.Success(it.toDomain())
                } ?: Resource.Error("Respuesta vacía del servidor")
            } else {
                Resource.Error("Error al insertar: ${response.message()}")
            }
        } catch (e: Exception) {
            Resource.Error("Excepción al insertar: ${e.message}")
        }
    }

    override suspend fun deletePaymentMethod(id: Int): Resource<Unit> {
        return try {
            val response = paymentService.deletePaymentMethod(id)
            if (response.isSuccessful) {
                val method = paymentDao.getPaymentMethodById(id).first()
                method?.let { paymentDao.deletePaymentMethod(it) }
                Resource.Success(Unit)
            } else {
                Resource.Error("Error al eliminar: ${response.message()}")
            }
        } catch (e: Exception) {
            Resource.Error("Excepción al eliminar: ${e.message}")
        }
    }
}