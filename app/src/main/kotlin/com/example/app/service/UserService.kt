package com.example.app.service

import com.example.core.model.User

interface UserService {
    fun saveUser(user: User): String

    fun getAllUsers(): List<User>
}
