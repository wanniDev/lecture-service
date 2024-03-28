package io.hhplus.lecture.enrollment.application

import io.hhplus.lecture.lesson.domain.LessonRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.test.Test
import kotlin.test.assertTrue

@SpringBootTest
class EnrollmentServiceConcurrencyTest @Autowired constructor(
    private val enrollmentService: EnrollmentService,
    private val lessonRepository: LessonRepository,
) {
    val log = LoggerFactory.getLogger(EnrollmentServiceConcurrencyTest::class.java)

    @Test
    fun `ensure concurrent enrollments do not exceed lesson limit`() {
        val lessonId = 1L // 미리 생성된 Lesson의 ID
        val userIds = (1L..31L).toList() // 테스트 사용자 ID 리스트
        val executor = Executors.newFixedThreadPool(userIds.size)
        var errorCount = 0

        // 동시에 수강 신청 시도
        userIds.forEach { userId ->
            executor.submit {
                try {
                    enrollmentService.enroll(userId, lessonId)
                } catch (e: Exception) {
                    log.error("초과된 수강신청 발생 $userId", e)
                    errorCount++
                }
            }
        }

        executor.shutdown()
        executor.awaitTermination(1, TimeUnit.MINUTES)

        val lesson = lessonRepository.findById(lessonId)
        assertTrue { lesson.enrollCount <= lesson.maximum }
        assertTrue { errorCount == (userIds.size - lesson.maximum).toInt() }
    }
}