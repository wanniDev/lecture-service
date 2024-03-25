package io.hhplus.lecture.user.domain

interface UserRepository {
    fun findById(id: Long): User
}