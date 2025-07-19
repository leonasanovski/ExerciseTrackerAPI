package com.sorsix.training.exercise_tracker.domain

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "users")
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    lateinit var id: UUID

    @Column(nullable = false, unique = true)
    lateinit var username: String

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var exercises: MutableList<Exercise> = mutableListOf()

    constructor()
    constructor(username: String) {
        this.username = username
    }
}