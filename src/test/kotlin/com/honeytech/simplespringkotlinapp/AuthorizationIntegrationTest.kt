package com.honeytech.simplespringkotlinapp

import com.honeytech.simplespringkotlinapp.model.web.WebUser
import org.junit.Assert
import org.junit.Test
import org.springframework.http.*
import org.springframework.web.client.exchange
import org.springframework.web.client.getForEntity


class AuthorizationIntegrationTest : AbstractIntegrationTest(){


    @Test
    fun authorizationTest() {

        // проверка недоступности ресурса
        val unauthorizedResult : ResponseEntity<String> = restTemplate.getForEntity("http://localhost:8080/api/greeting?name=ilya", String::class, "Yana")
        Assert.assertEquals(HttpStatus.UNAUTHORIZED, unauthorizedResult.statusCode)


        val authorizationResult : ResponseEntity<WebUser> = restTemplate.exchange(
                "http://localhost:8080/api/greeting?name=ilya",
                HttpMethod.POST,
                HttpEntity(WebUser::class),
                WebUser("honey256","123q123"))
        Assert.assertEquals(HttpStatus.OK, authorizationResult.statusCode)

        //var header : HttpHeaders = result.headers
        //var cookie = header.get(HttpHeaders.COOKIE)
        //println(cookie)

    }
}