package com.redwork.mercadolibre.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.redwork.mercadolibre.usescases.GetAllProductUseCase
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.given
import com.redwork.mercadolibre.domain.model.Address
import com.redwork.mercadolibre.domain.model.Product
import com.redwork.mercadolibre.domain.model.Seller
import com.redwork.mercadolibre.domain.model.Shipping
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ProductListViewModelTest {

    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var getAllCharacterUsesCase: GetAllProductUseCase

    @Mock
    lateinit var eventObserver: Observer<Event<ProductListViewModel.ProductListNavigation>>

    private lateinit var productListViewModel: ProductListViewModel

    @Before
    fun setup(){
        productListViewModel = ProductListViewModel(getAllCharacterUsesCase)
    }

    @Test
    fun `onGetAllProduct should return an expected success list if products`(){
        val expectedResult = listOf(mockedProducts.copy(id = "MLA1111890165"))
        given(getAllCharacterUsesCase.getItems(Mockito.anyString())).willReturn(Single.just(expectedResult))

        productListViewModel.events.observeForever(eventObserver)
        productListViewModel.onGetAllProducts(Mockito.anyString())

        verify(eventObserver).onChanged(Event(ProductListViewModel.ProductListNavigation.ShowProductList(expectedResult)))
    }
}

val mockedShipping = Shipping(
    ""
)

val mockedAddress = Address(
    "",
    "",
    ""
)

val mockedSeller = Seller(
    0,
    ""
)

val mockedProducts = Product(
    "",
    "",
    "",
    0.0,
    0,
    0,
    "",
    "",
    "",
    true,
    mockedAddress,
    mockedShipping,
    mockedSeller,
    ""
)