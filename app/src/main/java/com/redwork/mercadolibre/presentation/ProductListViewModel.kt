package com.redwork.mercadolibre.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.redwork.mercadolibre.domain.model.Product
import com.redwork.mercadolibre.usescases.GetAllProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(private val getAllCategoryUseCase: GetAllProductUseCase): ViewModel(){
    private var TAG: String = ProductListViewModel::class.java.name

    private val disposable = CompositeDisposable()

    private val queryValue = MutableLiveData<String>()


    private val _events = MutableLiveData<Event<ProductListNavigation>>()
    val events: LiveData<Event<ProductListNavigation>> get() = _events

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    fun  onRetryGetAllProducts(itemCount: Int, query: String) {
        if (itemCount > 0) {
            _events.value = Event(ProductListNavigation.HideLoading)
            return
        }

        onGetAllProducts(query)
    }

    fun getQuery(): String? {
        return queryValue.value
    }

    fun setQuery(query: String?) {
        queryValue.value = query
    }

    fun onGetAllProducts(query: String){
        disposable.add(
            getAllCategoryUseCase
                .getItems(query)
                .doOnSubscribe {
                    _events.value = Event(ProductListNavigation.ShowLoading)
                }
                .subscribe({ products ->
                    _events.value = Event(ProductListNavigation.HideLoading)
                    _events.value = Event(ProductListNavigation.ShowProductList(products))
                }, { error ->
                    _events.value = Event(ProductListNavigation.HideLoading)
                    Log.d(TAG, "onGetAllProducts: failed ${error.message}")
                    _events.value = Event(ProductListNavigation.ShowProductError("Ups, se ha producido un problema con tu solicitud, por favor vuelve a intentarlo."))
                })
        )
    }

    sealed class ProductListNavigation{
        data class ShowProductError(val error: String): ProductListNavigation()
        data class ShowProductList(val productList: List<Product>): ProductListNavigation()
        object HideLoading: ProductListNavigation()
        object ShowLoading: ProductListNavigation()
    }
}


