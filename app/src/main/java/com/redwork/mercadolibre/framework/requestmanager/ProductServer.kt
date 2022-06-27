package com.redwork.mercadolibre.framework.requestmanager

import android.os.Parcelable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.gson.annotations.SerializedName
import com.redwork.mercadolibre.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductServer(

    @SerializedName("id")
    val id: String? = null,

    @SerializedName("site_id")
    val siteId: String? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("price")
    val price: Double? = null,

    @SerializedName("available_quantity")
    val availableQuantity: Int? = null,

    @SerializedName("sold_quantity")
    val soldQuantity: Int? = null,

    @SerializedName("permalink")
    val permaLink: String? = null,

    @SerializedName("thumbnail")
    val thumbnail: String? = null,

    @SerializedName("condition")
    val condition: String? = null,

    @SerializedName("accepts_mercadopago")
    val acceptMercadoPago: Boolean? = null,

    @SerializedName("address")
    val address: AddressServer? = null,

    @SerializedName("shipping")
    val shipping: ShippingServer? = null,

    @SerializedName("seller")
    val seller: SellerServer? = null,

    @SerializedName("category_id")
    val categoryId: String? = null

): Parcelable

