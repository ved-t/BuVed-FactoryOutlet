package com.example.buved.di

import android.content.Context
import androidx.room.Room
import com.example.buved.core.constants.Constants
import com.example.buved.data.local.ProductIdDao
import com.example.buved.data.local.ProductIdDatabase
import com.example.buved.data.remote.FakeStoreApi
import com.example.buved.data.repository.AuthRepositoryImpl
import com.example.buved.data.repository.ProductRepositoryImpl
import com.example.buved.domain.repository.AuthRepository
import com.example.buved.domain.repository.ProductRepository
import com.example.buved.domain.usecase.auth.IsUserLoggedInUseCase
import com.example.buved.domain.usecase.auth.LoginUserUseCase
import com.example.buved.domain.usecase.auth.LogoutUserUseCase
import com.example.buved.domain.usecase.auth.RegisterUserUseCase
import com.example.buved.domain.usecase.cart.DeleteCartItemUseCase
import com.example.buved.domain.usecase.cart.GetAllCartItemsUseCase
import com.example.buved.domain.usecase.cart.InsertCartItemUseCase
import com.example.buved.domain.usecase.cart.UpdateCartItemUseCase
import com.example.buved.domain.usecase.productId.DeleteProductIdUseCase
import com.example.buved.domain.usecase.productId.GetAllProductIdUseCase
import com.example.buved.domain.usecase.fakestoreapi.GetProductsUseCase
import com.example.buved.domain.usecase.fakestoreapi.GetSingleProductUseCase
import com.example.buved.domain.usecase.productId.InsertProductIdUseCase
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

//    API providers
    @Provides
    fun provideGetProductsUseCase(repository: ProductRepository): GetProductsUseCase = GetProductsUseCase(repository)

    @Provides
    fun provideGetSingleProductUseCase(repository: ProductRepository): GetSingleProductUseCase = GetSingleProductUseCase(repository)

//    Product Id providers
    @Provides
    fun provideGetAllProductIdUseCase(repository: ProductRepository): GetAllProductIdUseCase = GetAllProductIdUseCase(repository)

    @Provides
    fun provideInsertProductIdUSeCase(repository: ProductRepository): InsertProductIdUseCase = InsertProductIdUseCase(repository)

    @Provides
    fun provideDeleteProductIdUSeCase(repository: ProductRepository): DeleteProductIdUseCase = DeleteProductIdUseCase(repository)

//    Cart provides
    @Provides
    fun provideInsertCartItemUseCase(repository: ProductRepository): InsertCartItemUseCase = InsertCartItemUseCase(repository)

    @Provides
    fun provideUpdateCartItemUseCase(repository: ProductRepository): UpdateCartItemUseCase = UpdateCartItemUseCase(repository)

    @Provides
    fun provideDeleteDeleteCartItemUseCase(repository: ProductRepository): DeleteCartItemUseCase = DeleteCartItemUseCase(repository)

    @Provides
    fun provideGetAllCartItems(repository: ProductRepository): GetAllCartItemsUseCase = GetAllCartItemsUseCase(repository)

//    Room providers
    @Provides
    fun provideProductIdDao(productIdDatabase: ProductIdDatabase): ProductIdDao{
        return productIdDatabase.productIdDao()
    }

    @Provides
    fun provideProductIdDatabase(@ApplicationContext context: Context): ProductIdDatabase{
        return Room.databaseBuilder(
            context.applicationContext,
            ProductIdDatabase::class.java,
            "product_id_database"
        )
            .fallbackToDestructiveMigration()
            .build()

    }

    @Provides
    fun provideProductRepository(api: FakeStoreApi, productIdDao: ProductIdDao): ProductRepository{
        return ProductRepositoryImpl(api, productIdDao)
    }

//    Auth repository
    @Provides
    fun provideAuthRepository(auth: FirebaseAuth): AuthRepository{
        return AuthRepositoryImpl(auth)
    }

//    Auth use case providers
    @Provides
    fun provideRegisterUSerUseCase(authRepository: AuthRepository): RegisterUserUseCase = RegisterUserUseCase(authRepository)

    @Provides
    fun provideLoginUSerUseCase(authRepository: AuthRepository): LoginUserUseCase = LoginUserUseCase(authRepository)

    @Provides
    fun provideLogoutUSerUseCase(authRepository: AuthRepository): LogoutUserUseCase = LogoutUserUseCase(authRepository)

    @Provides
    fun provideIsUserLoggedInUseCase(authRepository: AuthRepository): IsUserLoggedInUseCase = IsUserLoggedInUseCase(authRepository)

//    Api provider
    @Provides
    fun provideFakeStoreApi(): FakeStoreApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FakeStoreApi::class.java)
    }

//    Firebase providers
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth{
        return  Firebase.auth.also { it.useEmulator("192.168.1.6", 9099) }
    }
}