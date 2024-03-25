package io.hhplus.lecture.enrollment.application

import io.hhplus.lecture.enrollment.domain.Enrollment
import io.hhplus.lecture.enrollment.domain.EnrollmentRepository
import io.hhplus.lecture.lesson.domain.LessonRepository
import io.hhplus.lecture.user.domain.UserRepository
import org.springframework.stereotype.Service

@Service
class EnrollmentService(
    private val userRepository: UserRepository,
    private val lessonRepository: LessonRepository,
    private val enrollmentRepository: EnrollmentRepository
) {
    fun enroll(userId: Long, lessonId: Long) {
        val user = userRepository.findById(userId)
        val lesson = lessonRepository.findById(lessonId)

        when {
            lesson.isFull() -> throw IllegalStateException("수강 신청이 마감되었습니다.")
            lesson.isClosed() -> throw IllegalStateException("수강 신청 기간이 마감되었습니다.")
        }
        lesson.increaseCurrentEnrolledCount()
        enrollmentRepository.save(Enrollment.newInstance(user, lesson))
    }
}