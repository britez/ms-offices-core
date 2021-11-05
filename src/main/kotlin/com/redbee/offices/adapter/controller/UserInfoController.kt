package com.redbee.offices.adapter.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
class UserInfoController {

    @GetMapping("/user/info")
    fun getUserInfo(principal: Principal): Principal =
        principal
}