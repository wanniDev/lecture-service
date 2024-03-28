package io.hhplus.lecture.lesson.infrastructure.persistence

import io.hhplus.lecture.lesson.domain.Lesson
import io.hhplus.lecture.lesson.domain.LessonRepository
import io.hhplus.lecture.lesson.infrastructure.jpa.JpaLessonRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class LessonRepositoryComposition(
    private val jpaLessonRepository: JpaLessonRepository,
): LessonRepository {

    // 비관락 적용 테스트를 위해 임시로 작성한 Transactional 어노테이션
    @Transactional
    override fun findById(id: Long): Lesson {
        return jpaLessonRepository.findLessonById(id)
    }
}