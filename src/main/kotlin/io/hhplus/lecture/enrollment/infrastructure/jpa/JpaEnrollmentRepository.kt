package io.hhplus.lecture.enrollment.infrastructure.jpa

import io.hhplus.lecture.enrollment.domain.Enrollment
import io.hhplus.lecture.lesson.domain.Lesson
import io.hhplus.lecture.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface JpaEnrollmentRepository: JpaRepository<Enrollment, Long> {
    fun findIsAttendedByUserAndLesson(user: User, lesson: Lesson): Boolean
}