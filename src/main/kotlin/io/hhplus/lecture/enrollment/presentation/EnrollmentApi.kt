package io.hhplus.lecture.enrollment.presentation

import io.hhplus.lecture.enrollment.application.EnrollmentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v1/enrollments")
class EnrollmentApi(
    private val enrollmentService: EnrollmentService
) {
    @PostMapping("/enroll")
    fun enroll(@RequestBody enrollmentRequest: EnrollmentRequest):ResponseEntity<Void> {
        enrollmentService.enroll(enrollmentRequest.userId, enrollmentRequest.lessonId)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/is-enrolled")
    fun isAttended(@RequestBody enrollmentRequest: EnrollmentRequest):ResponseEntity<Boolean> {
        return ResponseEntity.ok(enrollmentService.isEnrolled(enrollmentRequest.userId, enrollmentRequest.lessonId))
    }
}
