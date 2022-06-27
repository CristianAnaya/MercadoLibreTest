package com.redwork.mercadolibre.di

import com.redwork.mercadolibre.framework.requestmanager.APIConstants
import com.redwork.mercadolibre.framework.requestmanager.APIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Named("BaseUrl")
    fun provideBaseUrl() = APIConstants.BASE_URL.toHttpUrl()


    // Creamos una unica instancia de retrofit para todo el contexto de la aplicación
    @Singleton
    @Provides
    fun provideRetrofitInstance(@Named("BaseUrl")baseUrl: HttpUrl): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Creamos una unica instancia de APIService para todo el contexto de la aplicacion
    // permitiendo inyectar esta dependencia otras capas del proyecto
    @Singleton
    @Provides
    fun restDataSource(retrofit: Retrofit): APIService =
        retrofit.create(APIService::class.java)

}