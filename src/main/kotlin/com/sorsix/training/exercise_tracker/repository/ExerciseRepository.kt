package com.sorsix.training.exercise_tracker.repository

import com.sorsix.training.exercise_tracker.domain.Exercise
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ExerciseRepository : JpaRepository<Exercise, Long>