package com.redwork.mercadolibre.domain.model

import com.google.gson.annotations.SerializedName

data class Search(

    @SerializedName("results")
    var products: List<Product>
)
{

}