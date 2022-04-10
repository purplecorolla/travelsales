package com.acme.tour

import com.acme.tour.model.TravelSale
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.concurrent.ConcurrentHashMap

@SpringBootApplication
class TourApplication{
	//criando map de promocoes
	companion object{
		val initialSales = arrayOf(
			TravelSale(1, "Venha conhecer L.A.", "Los Angeles",7,12.000,true),
			TravelSale(2, "Venha conhecer as belezas de Salvador", "Salvador", 7,6000.00,true),
			TravelSale(3, "Compre viagens para Miami com milhas!", "Miami", 0, 1000.00,false),
			TravelSale(4,"Viagens com hospedagem inclusa para Orlando!","Orlando", 7, 5000.00,true)
		)
	}
	//fica disponivel para todas as classes com @Bean
	@Bean
	fun sales() =
		ConcurrentHashMap<Long,TravelSale>(initialSales.associateBy(TravelSale::id))
}
fun main(args: Array<String>) {
	runApplication<TourApplication>(*args)
}
