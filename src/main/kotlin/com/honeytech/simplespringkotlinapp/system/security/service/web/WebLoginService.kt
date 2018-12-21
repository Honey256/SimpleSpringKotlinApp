package com.honeytech.simplespringkotlinapp.system.security.service.web

import com.honeytech.simplespringkotlinapp.model.web.WebUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component


@Component
class WebLoginService {

    @Autowired
    @Qualifier("authenticationManager")
    private lateinit var authManager: AuthenticationManager

    fun login(user : WebUser) : SecurityContext{
        val authReq = UsernamePasswordAuthenticationToken(user.username, user.password)
        val auth = authManager.authenticate(authReq)
        val securityContext = SecurityContextHolder.getContext()
        securityContext.authentication = auth
        return securityContext
    }
}
