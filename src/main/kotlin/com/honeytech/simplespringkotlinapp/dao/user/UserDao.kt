package com.honeytech.simplespringkotlinapp.dao.user

import com.honeytech.simplespringkotlinapp.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserDao : JpaRepository<User, Long> {
    fun findByUsername(username: String): User?
}