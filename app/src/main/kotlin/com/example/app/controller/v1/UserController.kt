package com.example.app.controller.v1

import com.example.app.service.UserService
import com.example.core.model.User
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController constructor(
    @Autowired private val userService: UserService
) {

    @GetMapping("/user/all")
    fun all(): ResponseEntity<Any> {
        return ResponseEntity.ok(userService.getAllUsers())
    }

    @PostMapping("/user/save", consumes = [MediaType.APPLICATION_JSON_VALUE])
    @Throws(Exception::class)
    fun save(
        @Valid @RequestBody
        user: User
    ): ResponseEntity<Any> {
        return ResponseEntity.ok(userService.saveUser(user))
    }
}
