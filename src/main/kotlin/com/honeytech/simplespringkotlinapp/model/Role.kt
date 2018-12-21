package com.honeytech.simplespringkotlinapp.model

import javax.persistence.*

@Entity
@Table(name = "role")
data class Role (

        @Enumerated(EnumType.STRING)
        val systemname: UserRole? = null
){

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null

    var description: String? = null

    @ManyToMany(mappedBy = "roles")
    val users: Set<User>? = null
}