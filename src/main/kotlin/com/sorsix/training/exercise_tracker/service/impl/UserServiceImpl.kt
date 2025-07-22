package com.sorsix.training.exercise_tracker.service.impl

import com.sorsix.training.exercise_tracker.domain.User
import com.sorsix.training.exercise_tracker.exception.NoUserFoundException
import com.sorsix.training.exercise_tracker.repository.UserRepository
import com.sorsix.training.exercise_tracker.service.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {
    override fun saveUser(username: String): User = userRepository.findByUsername(username)
        ?.let { throw NoUserFoundException("User with username '$username' already exists.") }
        ?: userRepository.save(User(username))
}