package io.hhplus.lecture.lesson.infrastructure.jpa

import io.hhplus.lecture.lesson.domain.Lesson
import org.springframework.data.jpa.repository.JpaRepository

interface JpaLessonRepository: JpaRepository<Lesson, Long> {
}