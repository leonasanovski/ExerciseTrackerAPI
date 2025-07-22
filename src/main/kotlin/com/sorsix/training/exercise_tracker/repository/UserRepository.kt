package com.sorsix.training.exercise_tracker.repository

import com.sorsix.training.exercise_tracker.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String): User?
    fun findById(id: UUID): User?
}
//TODO - ASK TOMCHE ABOUT USER? OR OPTIONAL<USER>, WHAT IS BETTER TO USE IN PRACTICE