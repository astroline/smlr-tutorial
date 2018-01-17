package ru.peterservice.kotlin.springboottutorial.config

import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class jacksonConfig {
    @Bean open fun kotlinModule() = KotlinModule()
}