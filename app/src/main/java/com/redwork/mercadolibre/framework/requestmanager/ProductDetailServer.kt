package com.redwork.mercadolibre.framework.requestmanager

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductDetailServer(

    @SerializedName("id")
    var id: String? = null,

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("price")
    var price: String? = null,

    @SerializedName("warranty")
    var warranty: String? = null,

    @SerializedName("pictures")
    var pictures: List<PictureServer>? = null,

    @SerializedName("attributes")
    var attributes: List<AttributeServer>? = null

): Parcelable