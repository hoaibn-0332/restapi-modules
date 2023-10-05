package com.example.core.model

import java.io.Serializable

data class Company(
    val id: Int? = null,
    val name: String,
    val users: List<User>
) : Serializable
