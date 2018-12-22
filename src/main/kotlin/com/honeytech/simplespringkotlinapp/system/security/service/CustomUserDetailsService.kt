package com.honeytech.simplespringkotlinapp.system.security.service


import com.honeytech.simplespringkotlinapp.dao.user.UserDao
import com.honeytech.simplespringkotlinapp.model.Role
import com.honeytech.simplespringkotlinapp.model.User
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component
import java.util.stream.Collectors
import javax.management.relation.RoleNotFoundException

@Component("customUserDetailsService")
class CustomUserDetailsService : UserDetailsService {
    @Autowired
    private lateinit var dao : UserDao

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username : String) : UserDetails {
        val user : User = dao.findByUsername(username) ?: throw UsernameNotFoundException(username)
        val authorities  = getAuthorities(user.roles ?: throw RoleNotFoundException())
        return org.springframework.security.core.userdetails.User(user.username, user.password, authorities)
    }

    private fun getAuthorities(roles : Set<Role>) : List<SimpleGrantedAuthority> {
        return roles.stream()
                .map{ role -> SimpleGrantedAuthority(role.systemname.toString()) }
                .collect(Collectors.toList())
    }
}