package io.hhplus.lecture.enrollment.infrastructure.persistence

import io.hhplus.lecture.enrollment.domain.Enrollment
import io.hhplus.lecture.enrollment.domain.EnrollmentRepository
import io.hhplus.lecture.enrollment.infrastructure.jpa.JpaEnrollmentRepository
import io.hhplus.lecture.lesson.domain.Lesson
import io.hhplus.lecture.user.domain.User
import org.springframework.stereotype.Repository

@Repository
class EnrollmentRepositoryComposition(
    private val jpaEnrollmentRepository: JpaEnrollmentRepository,
): EnrollmentRepository {
    override fun save(enrollment: Enrollment): Enrollment {
        return jpaEnrollmentRepository.save(enrollment)
    }

    override fun findIsAttendedByUserAndLesson(user: User, lesson: Lesson): Boolean {
        return jpaEnrollmentRepository.findIsAttendedByUserAndLesson(user, lesson)
    }
}