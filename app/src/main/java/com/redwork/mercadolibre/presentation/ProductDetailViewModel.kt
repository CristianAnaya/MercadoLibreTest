package com.redwork.mercadolibre.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.redwork.mercadolibre.framework.requestmanager.ProductDetailServer
import com.redwork.mercadolibre.framework.requestmanager.ProductServer
import com.redwork.mercadolibre.usescases.GetProductDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val getProductDetailUseCase: GetProductDetailUseCase
): ViewModel() {
    private var TAG: String = ProductDetailViewModel::class.java.name

    private val disposable = CompositeDisposable()

    private val _productDetail = MutableLiveData<ProductDetailServer>()
    val productDetail: LiveData<ProductDetailServer> get() = _productDetail

    private val _events = MutableLiveData<Event<ProductDetailNavigation>>()
    val events: LiveData<Event<ProductDetailNavigation>> get() = _events

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    // Nos suscribimos al evento para poder obtener los detalles del producto
    fun onGetProductDetail(id: String){
        disposable.add(
            getProductDetailUseCase
                .getProductDetail(id)
                ?.doOnSubscribe {
                    _events.value = Event(ProductDetailNavigation.ShowProductDetailLoading)
                }
                ?.subscribe({ product ->

                    _events.value = Event(ProductDetailNavigation.HideProductDetailLoading)
                    _events.value = Event(ProductDetailNavigation.ShowProductDetail(product!!))
                    _productDetail.value = product

                }, { error ->
                    _events.value = Event(ProductDetailNavigation.HideProductDetailLoading)
                    Log.d(TAG, "onGetProductDetail: failed ${error.message}")
                    _events.value = Event(ProductDetailNavigation.ShowProductError("Ups, se ha producido un problema con tu solicitud, por favor vuelve a intentarlo."))
                })!!
        )
    }


    sealed class ProductDetailNavigation {
        data class ShowProductError(val error: String) : ProductDetailNavigation()
        data class ShowProductDetail(val productDetailServer: ProductDetailServer) : ProductDetailNavigation()
        object HideProductDetailLoading : ProductDetailNavigation()
        object ShowProductDetailLoading : ProductDetailNavigation()
    }

}