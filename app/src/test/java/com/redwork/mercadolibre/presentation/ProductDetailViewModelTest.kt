package com.redwork.mercadolibre.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import com.redwork.mercadolibre.framework.requestmanager.AttributeServer
import com.redwork.mercadolibre.framework.requestmanager.PictureServer
import com.redwork.mercadolibre.framework.requestmanager.ProductDetailServer
import com.redwork.mercadolibre.usescases.GetProductDetailUseCase
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
class ProductDetailViewModelTest {

    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var getProductDetailUseCase: GetProductDetailUseCase

    @Mock
    lateinit var eventObserver: Observer<Event<ProductDetailViewModel.ProductDetailNavigation>>

    private lateinit var productDetailViewModel: ProductDetailViewModel

    @Before
    fun setup(){
        productDetailViewModel = ProductDetailViewModel(getProductDetailUseCase)
    }

    @Test
    fun `onGetProductDetail should return an expected success product detail`(){
        val expectedResult = mockedProductDetailServer.copy("MLA1111890165")
        given(getProductDetailUseCase.getProductDetail(Mockito.anyString())).willReturn(Single.just(expectedResult))

        productDetailViewModel.events.observeForever(eventObserver)
        productDetailViewModel.onGetProductDetail(Mockito.anyString())

        verify(eventObserver).onChanged(Event(ProductDetailViewModel.ProductDetailNavigation.ShowProductDetail(expectedResult)))
    }
}


val mockedAttributeServer = AttributeServer(
    "",
    "",
    "",
    ""
)

val mockedPictureServer = PictureServer(
    "",
    ""
)

val mockedProductDetailServer = ProductDetailServer(
    "",
    "",
    "",
    "",
    listOf(mockedPictureServer),
    listOf(mockedAttributeServer),
)