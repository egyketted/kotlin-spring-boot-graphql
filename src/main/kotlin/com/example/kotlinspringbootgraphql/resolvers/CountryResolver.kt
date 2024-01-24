package com.example.kotlinspringbootgraphql.resolvers

import com.example.kotlinspringbootgraphql.repository.CountryRepository
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class CountryResolver(@Autowired var repository: CountryRepository) {

    @QueryMapping
    fun getCountries(): List<Country> = repository.findAll().toList()

    @QueryMapping
    fun getCountriesByName(@Argument name: String): List<Country> = repository.findByName(name)

    @MutationMapping
    fun createCountry(@Argument name: String, @Argument population: Int, @Argument gdp: Int?): Country {
        val newCountry = Country()

        newCountry.name = name
        newCountry.population = population
        newCountry.gdp = gdp ?: 1

        return repository.save(newCountry)
    }

    @MutationMapping
    fun updateCountry(@Argument id: Int, @Argument name: String, @Argument population: Int, @Argument gdp: Int): Country {
        val outdatedCountry = repository.findById(id).orElseThrow()

        outdatedCountry.name = name
        outdatedCountry.population = population
        outdatedCountry.gdp = gdp

        return repository.save(outdatedCountry)
    }

    @MutationMapping
    fun deleteCountry(@Argument id: Int): Country {
        val deletedCountry = repository.findById(id).orElseThrow()

        repository.delete(deletedCountry)

        return deletedCountry
    }
}
@Entity(name = "countries")
class Country {
    @Id @GeneratedValue var id: Int = 0
    var name: String = ""
    var population: Int = 0
    var gdp: Int = 0
}