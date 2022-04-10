package com.acme.tour.controller

import com.acme.tour.model.TravelSale
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.ConcurrentHashMap

@RestController
class SaleController {
    @Autowired //o spring injeta o objeto "sales" aqui
    lateinit var sales : ConcurrentHashMap<Long, TravelSale>

    @RequestMapping(value = ["/sales/{id}"], method = [RequestMethod.GET])
    fun getSaleId(@PathVariable id:Long) = sales[id] //@PathVariable é uma anotação que "rastreia" a variavel e o tipo dela

    @RequestMapping(value = ["/sales/"], method = [RequestMethod.POST]) //post nao consegue ter {} na url
    fun createSale(@RequestBody sale: TravelSale)  {
        sales[sale.id] = sale
    } //é necessario passar a anotaçao @RequestBody para avisar o springboot q o json que irá vir é um "sale"
}
