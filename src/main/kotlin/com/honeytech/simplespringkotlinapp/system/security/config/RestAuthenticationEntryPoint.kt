package com.honeytech.simplespringkotlinapp.system.security.config

import org.springframework.security.core.AuthenticationException
import javax.servlet.http.HttpServletResponse
import java.io.IOException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest

class RestAuthenticationEntryPoint : AuthenticationEntryPoint {

    @Throws(IOException::class)
    override fun commence(
            request: HttpServletRequest,
            response: HttpServletResponse,
            authException: AuthenticationException) {

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized1")
    }
}
