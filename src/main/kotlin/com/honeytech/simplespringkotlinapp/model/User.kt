package com.honeytech.simplespringkotlinapp.model

import javax.persistence.*

@Entity
@Table(name = "table_user")
open class User (
        open var username : String = "",

        open var password : String = ""
) {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id : Long? = 0

    @ManyToMany
    @JoinTable(name = "user_roles",
            joinColumns = [JoinColumn(name = "user_id")],
            inverseJoinColumns = [JoinColumn(name = "role_id")])
    open var roles : Set<Role>? = null

    constructor(user : User) : this(user.username, user.password) {
        id = user.id
        roles = user.roles
    }
}
