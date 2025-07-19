package com.sorsix.training.exercise_tracker.service

import com.sorsix.training.exercise_tracker.domain.Exercise
import java.time.LocalDate
import java.util.UUID

interface ExerciseService {
    fun createExercise(id: String, description: String, duration: Int, date: LocalDate?) : Exercise
}