package com.example.tescontainers

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TescontainersApplication

fun main(args: Array<String>) {
    runApplication<TescontainersApplication>(*args)
}
