package com.honeytech.simplespringkotlinapp.system.security.controller

import javax.servlet.http.HttpServletRequest
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import com.honeytech.simplespringkotlinapp.model.web.WebUser
import com.honeytech.simplespringkotlinapp.system.security.service.web.WebLoginService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier

@RestController
@RequestMapping("/api")
class LoginController {

    @Autowired
    @Qualifier("webLoginService")
    private lateinit var loginService: WebLoginService

    @PostMapping(path = ["/login"],
                 produces = [MediaType.APPLICATION_JSON_VALUE],
                 consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun login(@RequestBody user : WebUser, request: HttpServletRequest) {
        val securityContext = loginService.login(user)
        val session = request.getSession(true)
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext)
    }
}
