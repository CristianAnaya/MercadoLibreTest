package com.redwork.mercadolibre.di

import com.redwork.mercadolibre.framework.requestmanager.APIService
import com.redwork.mercadolibre.usescases.GetProductDetailUseCase
import com.redwork.mercadolibre.usescases.GetProductDetailUseCaseImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ProductDetailModule {

    @Provides
    @Singleton
    fun productDetailRepository(apiService: APIService): GetProductDetailUseCase =
        GetProductDetailUseCaseImp(apiService)
}