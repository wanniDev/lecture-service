package io.hhplus.lecture.enrollment.application

import io.hhplus.lecture.enrollment.domain.Enrollment
import io.hhplus.lecture.enrollment.domain.EnrollmentRepository
import io.hhplus.lecture.lesson.domain.LessonRepository
import io.hhplus.lecture.user.domain.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class EnrollmentService(
    private val userRepository: UserRepository,
    private val lessonRepository: LessonRepository,
    private val enrollmentRepository: EnrollmentRepository
) {
    fun enroll(userId: Long, lessonId: Long) {
        val user = userRepository.findById(userId)
        val lesson = lessonRepository.findById(lessonId)

        val enrollment = enrollmentRepository.save(Enrollment.newInstance(user, lesson))
        when {
            lesson.isFull() -> throw IllegalStateException("수강 신청이 마감되었습니다.")
            lesson.isClosed() -> throw IllegalStateException("수강 신청 기간이 마감되었습니다.")
        }
        lesson.increaseCurrentEnrolledCount()
        enrollment.isAttended = true
    }

    fun isEnrolled(userId: Long, lessonId: Long): Boolean {
        val user = userRepository.findById(userId)
        val lesson = lessonRepository.findById(lessonId)
        return enrollmentRepository.findIsAttendedByUserAndLesson(user, lesson)
    }
}