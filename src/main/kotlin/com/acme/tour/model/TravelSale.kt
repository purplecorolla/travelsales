package com.acme.tour.model

data class TravelSale(
    val id: Long,
    val description: String,
    val local: String,
    val days: Int,
    var value: Double,
    val allInclusive: Boolean
)