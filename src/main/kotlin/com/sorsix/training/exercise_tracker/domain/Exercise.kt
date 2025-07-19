package com.sorsix.training.exercise_tracker.domain

import jakarta.persistence.*
import java.time.LocalDate
import java.util.UUID

@Entity
@Table(name = "exercises")
class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    lateinit var id: UUID

    @Column(nullable = false)
    lateinit var description: String

    @Column(nullable = false)
    var duration: Int = 0

    @Column(nullable = false)
    var date: LocalDate = LocalDate.now()

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    lateinit var user: User

    constructor()

    constructor(description: String, duration: Int, date: LocalDate = LocalDate.now(), user: User) {
        this.description = description
        this.duration = duration
        this.date = date
        this.user = user
    }
}