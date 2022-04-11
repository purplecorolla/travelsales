package com.acme.tour.service.impl

import com.acme.tour.model.TravelSale
import com.acme.tour.service.SaleService
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component //toda vez que o container subir ele olha todos com essa anotaçao e coloca todas as classes no repo de dependencias
// e ele instancia e deixa "pronto" para usar pelo @AutoWired
class SaleServiceImpl: SaleService {
    companion object{
        val initialSales = arrayOf(
            TravelSale(1, "Venha conhecer L.A.", "Los Angeles",7,12.000,true),
            TravelSale(2, "Venha conhecer as belezas de Salvador", "Salvador", 7,6000.00,true),
            TravelSale(3, "Compre viagens para Miami com milhas!", "Miami", 0, 1000.00,false),
            TravelSale(4,"Viagens com hospedagem inclusa para Orlando!","Orlando", 7, 5000.00,true)
        )
    }
    var sales =
        ConcurrentHashMap<Long,TravelSale>(initialSales.associateBy(TravelSale::id))

    override fun getSaleId(id: Long): TravelSale? {
        return sales[id]
    }

    override fun createSale(sale: TravelSale) {
        sales[sale.id] = sale
    }

    override fun deleteSale(id: Long) {
        sales.remove(id)
    }

    override fun updateSale(id: Long, sale: TravelSale) {
        deleteSale(id)
        createSale(sale)
    }

    override fun searchByLocal(local: String): List<TravelSale> =
        sales.filter {
            it.value.local.contains(local, true)
        }.map (Map.Entry<Long,TravelSale>::value).toList()
}