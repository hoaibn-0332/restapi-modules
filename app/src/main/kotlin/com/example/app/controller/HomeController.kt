package com.example.app.controller

import com.example.core.common.response.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController {

    @GetMapping("/")
    fun home(): ApiResponse<String> = ApiResponse.success("Home Page")
}
