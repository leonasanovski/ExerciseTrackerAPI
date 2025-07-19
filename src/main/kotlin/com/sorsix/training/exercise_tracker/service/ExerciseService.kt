package com.sorsix.training.exercise_tracker.service

import com.sorsix.training.exercise_tracker.domain.Exercise
import java.time.LocalDate

interface ExerciseService {
    fun createExercise(id: String, description: String, duration: Int, date: LocalDate?): Exercise
}