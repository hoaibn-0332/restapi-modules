package com.example.core.model

import jakarta.validation.constraints.NotBlank
import java.io.Serializable

data class User(
    val id: Int? = null,
    @field:NotBlank
    val name: String,
    val email: String
) : Serializable
