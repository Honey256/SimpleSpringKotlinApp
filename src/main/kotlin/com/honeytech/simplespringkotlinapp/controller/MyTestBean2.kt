package com.honeytech.simplespringkotlinapp.controller

import org.springframework.beans.factory.annotation.Autowired

open class MyTestBean2 (
        var name : String = "tina"
        ) {
    lateinit var myTestBean: MyTestBean
}