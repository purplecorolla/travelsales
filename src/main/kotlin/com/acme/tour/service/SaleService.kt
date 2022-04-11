package com.acme.tour.service

import com.acme.tour.model.TravelSale

interface SaleService {
    fun getSaleId(id: Long): TravelSale?
    fun createSale(sale: TravelSale)
    fun deleteSale(id: Long)
    fun updateSale(id: Long, sale: TravelSale)
    fun searchByLocal(local: String): List<TravelSale>
}