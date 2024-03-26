package io.hhplus.lecture.enrollment.domain

import io.hhplus.lecture.lesson.domain.Lesson
import io.hhplus.lecture.user.domain.User

interface EnrollmentRepository {
    fun save(enrollment: Enrollment): Enrollment
    fun findIsAttendedByUserAndLesson(user: User, lesson: Lesson): Boolean
}