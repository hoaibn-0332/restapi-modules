package com.example.core.database.model

import com.example.core.model.Company
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "company")
data class CompanyEntity(
    @Column(name = "name")
    val name: String
) : BaseEntity() {
    @OneToMany(mappedBy = "company")
    var users: MutableList<UserEntity> = mutableListOf()
}

fun CompanyEntity.toCompany() = Company(id = this.id?.toInt(), name = this.name, users = users.map { it.toUser() })
