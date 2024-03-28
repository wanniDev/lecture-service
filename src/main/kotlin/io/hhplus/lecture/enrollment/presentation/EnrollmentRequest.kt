package io.hhplus.lecture.enrollment.presentation

data class EnrollmentRequest(
    val userId: Long,
    val lessonId: Long
)