package io.hhplus.lecture.enrollment.application

import io.hhplus.lecture.enrollment.domain.Enrollment
import io.hhplus.lecture.enrollment.domain.EnrollmentRepository
import io.hhplus.lecture.lesson.domain.Lesson
import io.hhplus.lecture.lesson.domain.LessonRepository
import io.hhplus.lecture.user.domain.User
import io.hhplus.lecture.user.domain.UserRepository
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import java.time.LocalDate
import kotlin.test.Test
import kotlin.test.assertFailsWith

@ExtendWith(MockitoExtension::class)
class EnrollmentServiceTest {
    /**
     * 1. 수강 신청 api를 호출하면 enroll 메서드가 호출되는지 확인한다.
     * 2. 선착순 인원 제한수를 넘지 않는지 확인한다.
     * 3. 신청일자가 마감일자를 넘지 않는지 확인한다.
     * 3. 제한수와 마감일자를 넘지 않으면, 성공
     * 4. 제한수 혹은 마감일자를 넘으면, 실패
     * 5. 수강 신청 성공시, 수강신청 성공여부 true
     */
    @Test
    fun `should enroll`() {
        // given
        val user = User()
        user.id = 1
        val lesson = Lesson(30, 0, LocalDate.of(2024, 4, 20))
        lesson.id = 1
        val enrollment = Enrollment.newInstance(user, lesson)
        enrollment.id = 1
        val mockUserRepository = mock<UserRepository> {
            on { findById(any()) } doReturn user
        }
        val mockLessonRepository = mock<LessonRepository> {
            on { findById(any()) } doReturn lesson
        }
        val mockEnrollmentRepository = mock<EnrollmentRepository> {
            on { save(any()) } doReturn enrollment
        }
        val enrollmentService = EnrollmentService(mockUserRepository, mockLessonRepository, mockEnrollmentRepository)

        // when
        enrollmentService.enroll(user.id!!, lesson.id!!)

        // then
        verify(mockEnrollmentRepository).save(any())
        assert(enrollment.isAttended)
    }

    @Test
    fun `should throw IllegalStateException when lesson is full`() {
        // given
        val user = User()
        user.id = 1
        val lesson = Lesson(30, 30, LocalDate.of(2024, 4, 20))
        lesson.id = 1
        val enrollment = Enrollment.newInstance(user, lesson)
        enrollment.id = 1
        val mockUserRepository = mock<UserRepository> {
            on { findById(any()) } doReturn user
        }
        val mockLessonRepository = mock<LessonRepository> {
            on { findById(any()) } doReturn lesson
        }
        val mockEnrollmentRepository = mock<EnrollmentRepository> {
            on { save(any()) } doReturn enrollment
        }
        val enrollmentService = EnrollmentService(mockUserRepository, mockLessonRepository, mockEnrollmentRepository)

        // when
        val exception = assertFailsWith<IllegalStateException> {
            enrollmentService.enroll(user.id!!, lesson.id!!)
        }

        // then
        assert(exception.message == "수강 신청이 마감되었습니다.")
        assert(!enrollment.isAttended)
    }

    @Test
    fun `should throw IllegalStateException when lesson is closed`() {
        // given
        val user = User()
        user.id = 1
        val lesson = Lesson(30, 0, LocalDate.of(2020, 4, 20))
        lesson.id = 1
        val enrollment = Enrollment.newInstance(user, lesson)
        enrollment.id = 1
        val mockUserRepository = mock<UserRepository> {
            on { findById(any()) } doReturn user
        }
        val mockLessonRepository = mock<LessonRepository> {
            on { findById(any()) } doReturn lesson
        }
        val mockEnrollmentRepository = mock<EnrollmentRepository> {
            on { save(any()) } doReturn enrollment
        }
        val enrollmentService = EnrollmentService(mockUserRepository, mockLessonRepository, mockEnrollmentRepository)

        // when
        val exception = assertFailsWith<IllegalStateException> {
            enrollmentService.enroll(user.id!!, lesson.id!!)
        }

        // then
        assert(exception.message == "수강 신청 기간이 마감되었습니다.")
        assert(!enrollment.isAttended)
    }
}