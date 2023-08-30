package com.example.app.service

import com.example.core.database.UserRepository
import com.example.core.database.model.toEntity
import com.example.core.database.model.toUser
import com.example.core.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImpl constructor(
    @Autowired private val userRepository: UserRepository
) : UserService {
    override fun saveUser(user: User): String {
        userRepository.save(user.toEntity())
        return "Save user `${user.name}` successful"
    }

    override fun getAllUsers(): List<User> {
        return userRepository.findAll().map { it.toUser() }
    }
}
