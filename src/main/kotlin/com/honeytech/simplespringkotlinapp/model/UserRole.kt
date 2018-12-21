package com.honeytech.simplespringkotlinapp.model

enum class UserRole(val description : String){
    ADMIN("ADMIN"),
    PRESENTER("PRESENTER"),
    LISTENER("LISTENER");

    companion object {
        fun valueOf(name : String) : UserRole {
            for (role : UserRole in values()) {
               if (role.name == name) {
                   return role
               }
            }
            return LISTENER
        }
    }

    override fun toString() : String {
        return description
    }
}
