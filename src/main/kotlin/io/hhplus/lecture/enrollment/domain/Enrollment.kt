package io.hhplus.lecture.enrollment.domain

import io.hhplus.lecture.lesson.domain.Lesson
import io.hhplus.lecture.user.domain.User
import jakarta.persistence.*

@Entity
class Enrollment(
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    val user: User,
    @ManyToOne
    @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    val lesson: Lesson,
    var isAttended: Boolean = false
) {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    companion object {
        fun newInstance(user: User, lesson: Lesson): Enrollment {
            return Enrollment(user, lesson)
        }
    }
}