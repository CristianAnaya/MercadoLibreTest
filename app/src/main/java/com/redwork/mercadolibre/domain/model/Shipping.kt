package com.redwork.mercadolibre.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Shipping(

    val freeShipping: String? = null

): Parcelable