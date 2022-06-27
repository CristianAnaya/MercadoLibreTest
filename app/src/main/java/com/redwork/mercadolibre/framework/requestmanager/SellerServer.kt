package com.redwork.mercadolibre.framework.requestmanager

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SellerServer(

    @SerializedName("id") val id: Int? = null,

    @SerializedName("permalink") val permaLink: String? = null,

    ): Parcelable