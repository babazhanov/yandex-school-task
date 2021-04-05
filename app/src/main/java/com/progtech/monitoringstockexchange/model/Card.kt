package com.progtech.monitoringstockexchange.model

data class Card(
    val symbol: String,
    var image: String? = null,
    var companyName: String? = null,
    var price: String? = null,
    var deltaPrice: String? = null,
    var deltaPriceSign: Boolean = true
)
