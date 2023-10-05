package com.example.core.database.model

import com.example.core.model.User
import jakarta.persistence.*

@Entity
@Table(name = "user")
data class UserEntity(
    @Column(name = "name")
    val name: String,

    @Column(name = "email")
    val email: String? = null
) : BaseEntity() {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    var company: CompanyEntity? = null

    @OneToOne(mappedBy = "user", cascade = [CascadeType.ALL])
    @PrimaryKeyJoinColumn
    var token: ConfirmTokenEntity? = null
}

fun User.toEntity(): UserEntity = UserEntity(name = name, email = email)

fun UserEntity.toUser(): User = User(id = id?.toInt(), name = name, email = email ?: "", token = "")
