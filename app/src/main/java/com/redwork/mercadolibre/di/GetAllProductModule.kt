package com.redwork.mercadolibre.di

import com.redwork.mercadolibre.framework.requestmanager.APIService
import com.redwork.mercadolibre.usescases.GetAllProductUseCase
import com.redwork.mercadolibre.usescases.GetAllProductUseCaseImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class GetAllProductModule {

    @Provides
    @Singleton
    fun categoryRepository(apiService: APIService): GetAllProductUseCase =
        GetAllProductUseCaseImp(apiService)
}