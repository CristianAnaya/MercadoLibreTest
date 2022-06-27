package com.redwork.mercadolibre.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Product(

    val id: String? = null,

    val siteId: String? = null,

    val title: String? = null,

    val price: Double? = null,

    val availableQuantity: Int? = null,

    val soldQuantity: Int? = null,

    val permaLink: String? = null,

    val thumbnail: String? = null,

    val condition: String? = null,

    val acceptMercadoPago: Boolean? = null,

    val address: Address? = null,

    val shipping: Shipping? = null,

    val seller: Seller? = null,

    val categoryId: String? = null

): Parcelable


