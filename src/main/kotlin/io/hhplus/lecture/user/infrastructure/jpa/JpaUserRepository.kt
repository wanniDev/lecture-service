package io.hhplus.lecture.user.infrastructure.jpa

import io.hhplus.lecture.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface JpaUserRepository: JpaRepository<User, Long> {
    fun findUserById(id: Long): User
}