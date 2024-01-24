package com.example.kotlinspringbootgraphql.resolvers

import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Controller
class HelloResolver {

    @QueryMapping
    fun hello():String = "Hello World!"
}


@RestController
class HelloController {

    @GetMapping("/hello")
    fun hello():String = "Hello World!"
}