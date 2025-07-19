package com.sorsix.training.exercise_tracker.service

import com.sorsix.training.exercise_tracker.domain.User

interface UserService {
    fun saveUser(username: String): User
}