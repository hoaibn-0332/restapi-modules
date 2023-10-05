package com.example.core.database.model

import jakarta.persistence.*

@Entity
@Table(name = "confirm_token")
data class ConfirmTokenEntity(
    @Column(name = "token")
    val token: String
) : BaseEntity() {
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    val user: UserEntity? = null
}
