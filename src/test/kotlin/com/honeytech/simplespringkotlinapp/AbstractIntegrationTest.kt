package com.honeytech.simplespringkotlinapp

import org.junit.Before
import org.springframework.web.client.RestTemplate

abstract class AbstractIntegrationTest () {
    lateinit var restTemplate : RestTemplate
    @Before
    fun initTestData() {
        restTemplate = RestTemplate()
        restTemplate.errorHandler = CustomRestErrorHandler()
    }
}
