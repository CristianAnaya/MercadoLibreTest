package com.redwork.mercadolibre.usescases

import com.redwork.mercadolibre.domain.model.Product
import com.redwork.mercadolibre.framework.requestmanager.APIService
import com.redwork.mercadolibre.framework.requestmanager.ResultResponseServer
import com.redwork.mercadolibre.framework.requestmanager.toProductDomainList
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface GetAllProductUseCase {

    // Aqui haremos el llamado del caso de uso para obtener los items (productos)
    fun getItems(query: String): Single<List<Product>>

}

class GetAllProductUseCaseImp @Inject constructor(private val apiService: APIService): GetAllProductUseCase{

    // Realizaremos la implementacion del caso de uso para poder conectarnos a la api y obtener sus respuestas y ser enviado
    // a la capa de presentación
    override fun getItems(query: String): Single<List<Product>> {
        return apiService
            .getItemsCategory(query)
            .map(ResultResponseServer::toProductDomainList)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

}