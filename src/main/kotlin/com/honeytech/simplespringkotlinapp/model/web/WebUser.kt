package com.honeytech.simplespringkotlinapp.model.web

import com.honeytech.simplespringkotlinapp.model.Role

open class WebUser (
    open var username : String,

    open var password : String
) {
    var id : Long? = 0

    open var roles : Set<Role>? = null
}
