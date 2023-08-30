package com.example.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@ComponentScan(basePackages = ["com.example"])
@EnableJpaRepositories(basePackages = ["com.example"])
@EntityScan(basePackages = ["com.example"])
@ConfigurationPropertiesScan
class MainApplication

fun main(args: Array<String>) {
    runApplication<MainApplication>(*args)
}
