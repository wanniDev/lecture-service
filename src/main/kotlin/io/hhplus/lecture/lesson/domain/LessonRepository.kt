package io.hhplus.lecture.lesson.domain

interface LessonRepository {
    fun findById(id: Long): Lesson
}