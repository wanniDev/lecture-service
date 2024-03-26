package io.hhplus.lecture.user.infrastructure.persistence

import io.hhplus.lecture.user.domain.User
import io.hhplus.lecture.user.domain.UserRepository
import io.hhplus.lecture.user.infrastructure.jpa.JpaUserRepository
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryComposition(
    private val jpaUserRepository: JpaUserRepository
): UserRepository {
    override fun findById(id: Long): User {
        return jpaUserRepository.getReferenceById(id)
    }
}