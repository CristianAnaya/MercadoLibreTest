package com.redwork.mercadolibre.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.redwork.mercadolibre.adapter.AttributeAdapter
import com.redwork.mercadolibre.adapter.PicturesAdapter
import com.redwork.mercadolibre.databinding.ActivityProductDetailBinding
import com.redwork.mercadolibre.domain.model.Product
import com.redwork.mercadolibre.framework.requestmanager.ProductDetailServer
import com.redwork.mercadolibre.presentation.ProductDetailViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProductDetailActivity : AppCompatActivity() {
    private var TAG: String = ProductDetailActivity::class.java.name

    private lateinit var binding: ActivityProductDetailBinding
    private val viewModel: ProductDetailViewModel by viewModels()

    private lateinit var attributeAdapter: AttributeAdapter
    private lateinit var pictureListAdapter: PicturesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extra = intent.extras
        if (extra != null) {
            val product: Product? = extra.getParcelable("product")
            viewModel.onGetProductDetail(product?.id!!)
        }
        viewModel.productDetail.observe(this, Observer(this::loadProductDetail))

    }

    private fun loadProductDetail(productDetailServer: ProductDetailServer) {

        // Actualizo los datos
        binding.txtViewNombreProducto.text = productDetailServer.title

        binding.txtViewPrecioProducto.text =
            if (productDetailServer.price != null) "$ " + productDetailServer.price else ""

        binding.txtViewGarantiaProducto.text = productDetailServer.warranty

        pictureListAdapter = PicturesAdapter(productDetailServer.pictures!!)
        binding.recyclerViewImagesProducto.adapter = pictureListAdapter

        attributeAdapter = AttributeAdapter(productDetailServer.attributes!!)
        binding.recyclerViewAtributosProducto.adapter = attributeAdapter

        binding.progressBarProductDetail.visibility = View.INVISIBLE
        binding.txtViewCaracteristicas.visibility = View.VISIBLE
        binding.linearLayoutDescription.visibility = View.VISIBLE
    }
}