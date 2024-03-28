package io.hhplus.lecture.lesson.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDate

@Entity
class Lesson(
    val maximum: Long,
    var enrollCount: Long,
    val dueDate: LocalDate
) {
    fun isFull(): Boolean {
        return maximum <= enrollCount
    }

    fun isClosed(): Boolean {
        return LocalDate.now().isAfter(dueDate)
    }

    fun increaseCurrentEnrolledCount() {
        this.enrollCount++
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}