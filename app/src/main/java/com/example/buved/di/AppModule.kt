package com.example.buved.di

import com.example.buved.core.constants.Constants
import com.example.buved.data.remote.FakeStoreApi
import com.example.buved.data.repository.ProductRepositoryImpl
import com.example.buved.domain.repository.ProductRepository
import com.example.buved.domain.usecase.GetProductsUseCase
import com.example.buved.domain.usecase.GetSingleProductUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideGetProductsUseCase(repository: ProductRepository): GetProductsUseCase = GetProductsUseCase(repository)

    @Provides
    fun provideGetSingleProductUseCase(repository: ProductRepository): GetSingleProductUseCase = GetSingleProductUseCase(repository)

    @Provides
    fun provideProductRepository(api: FakeStoreApi): ProductRepository{
        return ProductRepositoryImpl(api)
    }

    @Provides
    fun provideFakeStoreApi(): FakeStoreApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FakeStoreApi::class.java)
    }
}