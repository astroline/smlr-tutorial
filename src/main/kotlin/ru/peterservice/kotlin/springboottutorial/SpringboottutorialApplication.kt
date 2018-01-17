package ru.peterservice.kotlin.springboottutorial

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class SpringboottutorialApplication

fun main(args: Array<String>) {
    SpringApplication.run(SpringboottutorialApplication::class.java, *args)
}
