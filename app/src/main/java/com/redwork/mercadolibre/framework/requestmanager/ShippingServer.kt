package com.redwork.mercadolibre.framework.requestmanager

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShippingServer(

    @SerializedName("free_shipping") val freeShipping: String? = null

): Parcelable