package com.honeytech.simplespringkotlinapp

import com.honeytech.simplespringkotlinapp.controller.MyTestBean
import com.honeytech.simplespringkotlinapp.controller.MyTestBean2
import com.honeytech.simplespringkotlinapp.controller.MyTestBean3
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.ImportResource

@SpringBootApplication
class SimpleSpringKotlinAppApplication

fun main(args: Array<String>) {
	//runApplication<SimpleSpringKotlinAppApplication>(*args)
	 var context : ApplicationContext = SpringApplication.run(SimpleSpringKotlinAppApplication::class.java, *args)
	for (beanDefName : String in context.beanDefinitionNames) {
		println(beanDefName)
	}
}

@Configuration
@ImportResource(locations = ["classpath:context.groovy"])
class AppConfiguration