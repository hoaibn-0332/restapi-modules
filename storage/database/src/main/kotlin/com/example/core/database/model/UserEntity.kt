package com.example.core.database.model

import com.example.core.model.User
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "user")
data class UserEntity(
    @Column(name = "name")
    val name: String,

    @Column(name = "email")
    val email: String? = null
) : BaseEntity()

fun User.toEntity(): UserEntity = UserEntity(name = name, email = email)

fun UserEntity.toUser(): User = User(id = id?.toInt(), name = name, email = email ?: "")