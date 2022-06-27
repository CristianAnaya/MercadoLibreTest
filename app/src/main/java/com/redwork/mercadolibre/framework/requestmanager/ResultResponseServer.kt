package com.redwork.mercadolibre.framework.requestmanager

import com.google.gson.annotations.SerializedName


data class ResultResponseServer(

    @SerializedName("results")
    var products: List<ProductServer>

)