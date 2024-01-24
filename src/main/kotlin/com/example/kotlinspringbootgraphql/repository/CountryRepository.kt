package com.example.kotlinspringbootgraphql.repository

import com.example.kotlinspringbootgraphql.resolvers.Country
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CountryRepository: CrudRepository<Country, Int> {

    fun findByName(name: String): List<Country>
}