package com.honeytech.simplespringkotlinapp

import org.junit.Assert
import org.junit.Test
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.getForEntity


class AuthorizationIntegrationTest : AbstractIntegrationTest(){


    @Test
    fun authorizationTest() {

        // проверка недоступности ресурса
        val result : ResponseEntity<String> = restTemplate.getForEntity("http://localhost:8080/api/greeting?name=ilya", String::class, "Yana")
        Assert.assertEquals(HttpStatus.UNAUTHORIZED, result.statusCode)


    }
}