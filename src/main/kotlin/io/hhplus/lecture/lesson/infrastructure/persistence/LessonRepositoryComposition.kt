package io.hhplus.lecture.lesson.infrastructure.persistence

import io.hhplus.lecture.lesson.domain.Lesson
import io.hhplus.lecture.lesson.domain.LessonRepository
import io.hhplus.lecture.lesson.infrastructure.jpa.JpaLessonRepository
import org.springframework.stereotype.Repository

@Repository
class LessonRepositoryComposition(
    private val jpaLessonRepository: JpaLessonRepository,
): LessonRepository {
    override fun findById(id: Long): Lesson {
        return jpaLessonRepository.findLessonById(id)
    }
}