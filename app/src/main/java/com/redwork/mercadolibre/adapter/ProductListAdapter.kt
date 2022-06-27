package com.redwork.mercadolibre.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.redwork.mercadolibre.R
import com.redwork.mercadolibre.databinding.ProductItemBinding
import com.redwork.mercadolibre.domain.model.Product
import com.redwork.mercadolibre.framework.imagemanager.bindImageUrl
import com.redwork.mercadolibre.presentation.ProductListViewModel
import com.redwork.mercadolibre.ui.ProductDetailActivity
import com.redwork.mercadolibre.utils.startActivity

class ProductListAdapter (
    val context: Context,
): RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder>() {
    private var TAG: String = ProductListViewModel::class.java.name

    private val productList: MutableList<Product> = mutableListOf()

    fun addNewData(newData: List<Product>) {
        productList.clear()
        productList.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder{
        val itemBinding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductListViewHolder(itemBinding)
    }


    override fun getItemCount() = productList.size

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        val product = productList[position]

        holder.bind(product)

        holder.itemView.setOnClickListener { goToProductDetail(product) }

    }

    private fun goToProductDetail(product: Product) {
        context.startActivity<ProductDetailActivity> {
            putExtra("product", product)
        }
    }

    class ProductListViewHolder(
        private var dataBinding: ProductItemBinding,
    ): RecyclerView.ViewHolder(dataBinding.root) {
        private var TAG: String = ProductListViewHolder::class.java.name

        //region Public Methods
        fun bind(item: Product){
            dataBinding.product = item
            dataBinding.imgViewMiniatura.bindImageUrl(
                url = item.thumbnail,
                placeholder = R.drawable.ic_camera_alt_black,
                errorPlaceholder = R.drawable.ic_404
            )
        }

    }
}