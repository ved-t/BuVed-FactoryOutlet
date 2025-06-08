package com.example.buved.domain.usecase

import android.util.Log
import com.example.buved.data.remote.dto.ProductsDTOItem
import com.example.buved.data.remote.dto.toDomain
import com.example.buved.domain.model.Product
import com.example.buved.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
){
    operator fun invoke(): Flow<List<Product>> = flow {
        try {
            emit(productRepository.getProducts().map { it.toDomain() })
        }
        catch (e: Exception){
            emit(emptyList())
            Log.e("ProductUseCase", e.toString())
        }
    }
}

