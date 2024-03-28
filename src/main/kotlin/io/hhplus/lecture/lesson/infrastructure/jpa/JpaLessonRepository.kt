package io.hhplus.lecture.lesson.infrastructure.jpa

import io.hhplus.lecture.lesson.domain.Lesson
import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock

interface JpaLessonRepository: JpaRepository<Lesson, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    fun findLessonById(id: Long): Lesson
}