package com.redwork.mercadolibre.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Address(

    val stateId: String? = null,

    val stateName: String? = null,

    val cityName: String? = null
): Parcelable