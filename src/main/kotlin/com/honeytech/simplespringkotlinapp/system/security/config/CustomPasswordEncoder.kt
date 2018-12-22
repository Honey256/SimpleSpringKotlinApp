package com.honeytech.simplespringkotlinapp.system.security.config

import org.springframework.security.crypto.password.PasswordEncoder

class CustomPasswordEncoder {
    lateinit var encoder : PasswordEncoder
}