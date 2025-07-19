package com.sorsix.training.exercise_tracker.service.impl

import com.sorsix.training.exercise_tracker.domain.Exercise
import com.sorsix.training.exercise_tracker.repository.ExerciseRepository
import com.sorsix.training.exercise_tracker.repository.UserRepository
import com.sorsix.training.exercise_tracker.service.ExerciseService
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
class ExerciseServiceImpl(
    private val exerciseRepository: ExerciseRepository,
    private val userRepository: UserRepository
) : ExerciseService {
    override fun createExercise(id: String, description: String, duration: Int, date: LocalDate?): Exercise {
        val user = userRepository.findById(UUID.fromString(id))
        if (user != null) {
            val exercise = Exercise(
                description = description,
                duration = duration,
                date = date ?: LocalDate.now(),
                user = user,
            )
            exerciseRepository.save(exercise)
            return exercise
        }
        throw RuntimeException("User with id=$id doesn't exist")
    }
}