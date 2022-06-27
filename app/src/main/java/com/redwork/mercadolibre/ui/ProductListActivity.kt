package com.redwork.mercadolibre.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.haerul.bottomfluxdialog.BottomFluxDialog
import com.redwork.mercadolibre.adapter.ProductListAdapter
import com.redwork.mercadolibre.databinding.ActivityProductListBinding
import com.redwork.mercadolibre.presentation.Event
import com.redwork.mercadolibre.presentation.ProductListViewModel
import com.redwork.mercadolibre.utils.showLongToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListActivity : AppCompatActivity() {
    private var TAG: String = ProductListActivity::class.java.name

    private lateinit var binding: ActivityProductListBinding

    private lateinit var productListAdapter: ProductListAdapter

    private val viewModel: ProductListViewModel by viewModels()

    private var product = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerProduct.layoutManager = LinearLayoutManager(this)

        productListAdapter = ProductListAdapter(this)

        binding.recyclerProduct.run {
            adapter = productListAdapter
        }

        // Realizamos la misma consulta persitiendo el valor en la actividad
        if (!viewModel.getQuery().isNullOrBlank()){
            viewModel.onGetAllProducts(viewModel.getQuery()!!)
            binding.collapsing.title = viewModel.getQuery()
            viewModel.events.observe(this, Observer(this::loadProducts))
        }

        binding.search.setOnClickListener {
            showDialogSearch()
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.onRetryGetAllProducts(binding.recyclerProduct.adapter?.itemCount ?: 0, product)
        }
    }

    // Obtendremos los resultados de la busqueda del producto, en el cual mostrara una lista en caso de que sea exitosa
    // la respuesta o si ocurre algun error en el proceso.
    private fun loadProducts(event: Event<ProductListViewModel.ProductListNavigation>?){
        event?.getContentIfNotHandled()?.let { navigation ->
            when (navigation) {

                is ProductListViewModel.ProductListNavigation.ShowProductError -> navigation.run{
                    showLongToast(error)
                    binding.emptyView.visibility = View.VISIBLE
                    binding.shimmer.visibility = View.GONE
                }

                is ProductListViewModel.ProductListNavigation.ShowProductList -> navigation.run {
                    productListAdapter.addNewData(productList)
                }

                is ProductListViewModel.ProductListNavigation.HideLoading -> {
                    binding.swipeRefresh.isRefreshing = false
                    binding.shimmer.stopShimmer()
                    binding.shimmer.visibility = View.GONE
                }

                is ProductListViewModel.ProductListNavigation.ShowLoading -> {
                    binding.swipeRefresh.isRefreshing = true
                    binding.shimmer.startShimmer()
                    binding.emptyView.visibility = View.GONE
                }
            }
        }
    }


    //Mostramos el cuadro de dialogo con el que nos permitira realizar las busqueda de los productos
    private fun showDialogSearch() {

        BottomFluxDialog.inputDialog(this)
            .setTextTitle("Buscar")
            .setTextMessage("¿Que producto estas buscando?")
            .setRightButtonText("Consultar")
            .setLeftButtonText("Cancelar")
            .setInputListener(object : BottomFluxDialog.OnInputListener {
                override fun onSubmitInput(text: String) {
                    if (text.isEmpty()) {
                        Toast.makeText(
                            this@ProductListActivity,
                            "Debes escribir el producto que deseas buscar",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        binding.collapsing.title = text
                        viewModel.onGetAllProducts(text)
                        viewModel.setQuery(text)
                        viewModel.events.observe(this@ProductListActivity) { events -> loadProducts(events) }
                    }
                }

                override fun onCancelInput() {
                    Toast.makeText(
                        this@ProductListActivity,
                        "Cancelaste la busqueda",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
            .show()
    }
}