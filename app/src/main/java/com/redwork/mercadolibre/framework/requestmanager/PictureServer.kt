package com.redwork.mercadolibre.framework.requestmanager

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PictureServer(

    @SerializedName("id")
    val id: String? = null,

    @SerializedName("url")
    val url: String? = null

): Parcelable {
}