package com.redwork.mercadolibre.framework.requestmanager

import com.redwork.mercadolibre.domain.model.*

/**
 * Devuelve una lista que contiene los resultados de aplicar
 * la función de transformación dada una lista de producto de la colección original.
 */
fun ResultResponseServer.toProductDomainList(): List<Product> = products.map {
    it.run{
        Product(
            id,
            siteId,
            title,
            price,
            availableQuantity,
            soldQuantity,
            permaLink,
            thumbnail,
            condition,
            acceptMercadoPago,
            address?.toAddressDomain(),
            shipping?.toShippingDomain(),
            seller?.toSellerDomain(),
            categoryId
        )
    }
}

fun AddressServer.toAddressDomain() = Address(
    stateId,
    stateName,
    cityName
)

fun ShippingServer.toShippingDomain() = Shipping(
    freeShipping
)

fun SellerServer.toSellerDomain() = Seller(
    id,
    permaLink
)

// Mappers ProductDetail







