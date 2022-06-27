package com.redwork.mercadolibre.usescases

import com.redwork.mercadolibre.framework.requestmanager.APIService
import com.redwork.mercadolibre.framework.requestmanager.ProductDetailServer
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface GetProductDetailUseCase {

    fun getProductDetail(id: String): Single<ProductDetailServer?>?
}


class GetProductDetailUseCaseImp @Inject constructor(private val apiService: APIService): GetProductDetailUseCase{

    override fun getProductDetail(id: String): Single<ProductDetailServer?>? {
        return apiService
            .getProductDetail(id)
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())
    }

}
