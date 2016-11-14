package ru.finntrolle

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class GohomeApplication

fun main(args: Array<String>) {
    SpringApplication.run(GohomeApplication::class.java, *args)
}
