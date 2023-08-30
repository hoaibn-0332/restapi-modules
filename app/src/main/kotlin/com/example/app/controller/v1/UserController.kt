package com.example.app.controller.v1

import com.example.core.common.error.ErrorType
import com.example.core.common.response.ApiResponse
import com.example.core.database.UserRepository
import com.example.core.database.model.toEntity
import com.example.core.database.model.toUser
import com.example.core.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {

    @Autowired
    lateinit var userRepository: UserRepository

    @GetMapping("/user/all")
    fun all(): ApiResponse<Any> {
        val users = userRepository.findAll().map { it.toUser() }
        return ApiResponse.success(users)
    }

    @PostMapping("/user/save")
    fun save(@RequestBody user: Map<String, String>): ApiResponse<Any> {
        val name = user["name"]
        val email = user["email"]
        return if (name.isNullOrBlank()) {
            ApiResponse.error(ErrorType.DEFAULT_ERROR, "Name must not nul")
        } else {
            userRepository.save(User(name = name, email = email ?: "").toEntity())
            ApiResponse.success("Save user successful")
        }
    }
}
