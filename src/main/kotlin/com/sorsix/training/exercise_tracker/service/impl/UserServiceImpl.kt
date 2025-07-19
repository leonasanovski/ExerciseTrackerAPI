package com.sorsix.training.exercise_tracker.service.impl

import com.sorsix.training.exercise_tracker.domain.User
import com.sorsix.training.exercise_tracker.repository.UserRepository
import com.sorsix.training.exercise_tracker.service.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {
    override fun saveUser(username: String): User {
        val existingUser = userRepository.findByUsername(username)
        if (existingUser != null) {
            throw RuntimeException("User with username=$username already exists in the User Database")
        }
        val newUser = User(username)
        return userRepository.save(newUser)
    }
}