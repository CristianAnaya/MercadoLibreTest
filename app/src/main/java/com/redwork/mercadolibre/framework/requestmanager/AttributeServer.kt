package com.redwork.mercadolibre.framework.requestmanager

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class AttributeServer(

    @SerializedName("id")
    val id: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("value_id")
    val value_id: String? = null,

    @SerializedName("value_name")
    val value_name: String? = null

): Parcelable