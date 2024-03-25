package io.hhplus.lecture.enrollment.application

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.verify
import kotlin.test.Test

@ExtendWith(MockitoExtension::class)
class EnrollmentServiceTest {
        @Test
    fun `should enroll`() {
        // given
        val enrollmentService = EnrollmentService()

        // when
        enrollmentService.enroll()

        // then
        verify(enrollmentService).enroll()
    }
}