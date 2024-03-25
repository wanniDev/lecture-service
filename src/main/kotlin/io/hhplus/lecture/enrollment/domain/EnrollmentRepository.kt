package io.hhplus.lecture.enrollment.domain

interface EnrollmentRepository {
    fun save(enrollment: Enrollment): Enrollment
}