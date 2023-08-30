package com.example.core.database

import com.example.core.database.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableJpaRepositories(basePackages = ["com.example"])
interface UserRepository : JpaRepository<UserEntity, Long>
