package com.example.axon

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AxonApplication

fun main(args: Array<String>) {
    println("Tutorial from https://sgitario.github.io/axon-by-example/")
    runApplication<AxonApplication>(*args)
}
