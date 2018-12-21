package com.honeytech.simplespringkotlinapp.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component("myTestBean3")
class MyTestBean3 (
        var name : String = "tina"
)