package com.redwork.mercadolibre.framework.requestmanager

import com.redwork.mercadolibre.framework.requestmanager.APIConstants.GET_ITEMS_CATEGORY
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

    /** SITE_ID: Consulta de valores
     * @see <a href = "https://developers.mercadolibre.com.ar/es_ar/llego-catalogo-conoce-como-adaptar-tu-integracion" />  opciones de Paises para site_id </a>
     * site_id: string que representa el país. Obligatorio. Ejemplos:
     *          MLA - > Argentina
     *          MLM - > Mexico
     *          MLB - > Brasil
     *
     * A los fines del ejercicio se deja constante en MLA
     * */


    /**
     * Busqueda de un Producto
     * Tener en cuenta: se debe realizar la consulta formada por -> BASE_URL + GET_ITEMS_CATEGORY + q (query)
     *
     * @param query  -> Lo que se desea buscar
     * @see <a href = "https://developers.mercadolibre.com.ar/es_ar/pagina-de-resultados" />  Paginar Resultados </a>
     */

    @GET(GET_ITEMS_CATEGORY)
    fun getItemsCategory(
        @Query("q") query: String
    ): Single<ResultResponseServer>

    /**
     * Consultar por los detalles de un Producto
     * La consulta se debe realizar -> BASE_URL + {ID_PRODUCTO}
     *
     * @param id -> id del producto a buscar
     */
    @GET("items/{id}")
    fun getProductDetail(@Path("id") id: String?): Single<ProductDetailServer?>?

}