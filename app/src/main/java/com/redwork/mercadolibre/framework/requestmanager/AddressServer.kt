package com.redwork.mercadolibre.framework.requestmanager

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AddressServer(

    @SerializedName("state_id") val stateId: String? = null,

    @SerializedName("state_name") val stateName: String? = null,

    @SerializedName("city_name") val cityName: String? = null
): Parcelable