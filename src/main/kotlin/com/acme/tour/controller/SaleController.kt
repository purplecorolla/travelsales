package com.acme.tour.controller

import com.acme.tour.model.TravelSale
import com.acme.tour.service.SaleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.ConcurrentHashMap
//@PathVariable é uma anotação que "rastreia" a variavel e o tipo dela
@RestController
@RequestMapping (value = ["/sales"])
class SaleController {
    @Autowired
    lateinit var saleService: SaleService

    @GetMapping() // lista as promocoes filtrando por local
    fun getAll(@RequestParam(required = false, defaultValue = "") localFilter: String) =
        this.saleService.searchByLocal(localFilter)

    @GetMapping("/{id}")
    fun getSaleId(@PathVariable id: Long): ResponseEntity<TravelSale?> {
        var sale = this.saleService.getSaleId(id)
        var status = if(sale == null) HttpStatus.NOT_FOUND else HttpStatus.OK
        return ResponseEntity(sale, status)
        //caso a requisicao for invalida, ira retornar NOT_FOUND
    }
    @PostMapping()
    fun createSale(@RequestBody sale: TravelSale): ResponseEntity<TravelSale> {
        var sale = this.saleService.createSale(sale)
        return ResponseEntity(HttpStatus.CREATED)
    } //fazer a logica para quando der algum id ja existente
    //é necessario passar a anotaçao @RequestBody para avisar o springboot q o json que irá vir é um "sale"

    @DeleteMapping("/{id}")
    fun deleteSale(@PathVariable id: Long): ResponseEntity<TravelSale> {
        var deleteSale = this.saleService.deleteSale(id)
        return ResponseEntity(HttpStatus.ACCEPTED)
    }
    @PutMapping("/{id}")
    fun updateSale(@PathVariable id: Long, @RequestBody sale : TravelSale): ResponseEntity<TravelSale>{
        this.saleService.updateSale(id, sale)
        return ResponseEntity(HttpStatus.OK)
    }
}
