package com.sorsix.training.exercise_tracker.api

import com.sorsix.training.exercise_tracker.repository.UserRepository
import com.sorsix.training.exercise_tracker.service.ExerciseService
import com.sorsix.training.exercise_tracker.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userService: UserService,
    private val userRepository: UserRepository,
    private val exerciseService: ExerciseService,
) {
    val formatter: DateTimeFormatter? = DateTimeFormatter.ofPattern("E MMM dd yyyy", Locale.ENGLISH)

    @PostMapping
    fun createUser(@RequestParam username: String): ResponseEntity<Any> {
        val user = userService.saveUser(username)
        return ResponseEntity.ok(
            mapOf("username" to user.username, "_id" to user.id.toString())
        )
    }

    @GetMapping
    fun getAllUsers(): ResponseEntity<Any> = ResponseEntity.ok(userRepository.findAll().map {
        mapOf(
            "username" to it.username,
            "_id" to it.id.toString()
        )
    })

    @PostMapping("/{id}/exercises")
    fun createExercise(
        @PathVariable id: String,
        @RequestParam description: String,
        @RequestParam duration: Int,
        @RequestParam(required = false) date: String?
    ): ResponseEntity<Any> {
        val parsedDate = date?.let { LocalDate.parse(it) }
        val createdExercise = exerciseService.createExercise(id, description, duration, parsedDate)
        val response = mapOf(
            "username" to createdExercise.user.username,
            "_id" to createdExercise.user.id.toString(),
            "description" to createdExercise.description,
            "duration" to createdExercise.duration,
            "date" to createdExercise.date.format(formatter),
        )
        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}/logs")
    fun getUserLogs(
        @PathVariable id: String,
        @RequestParam(required = false) from: String?,
        @RequestParam(required = false) to: String?,
        @RequestParam(required = false) limit: Int?
    ): ResponseEntity<Any> {
        val user = userRepository.findById(UUID.fromString(id))
            ?: return ResponseEntity.badRequest().body("User with id = $id not found")

        var logs = user.exercises.sortedBy { it.date }
        from?.let {
            val fromDate = LocalDate.parse(it)
            logs = logs.filter { ex -> ex.date >= fromDate }
        }
        to?.let {
            val toDate = LocalDate.parse(it)
            logs = logs.filter { ex -> ex.date <= toDate }
        }
        if (limit != null) {
            logs = logs.take(limit)
        }
        val logList = logs.map {
            mapOf(
                "description" to it.description,
                "duration" to it.duration,
                "date" to it.date.format(formatter)
            )
        }

        return ResponseEntity.ok(
            mapOf(
                "username" to user.username,
                "count" to logList.size,
                "_id" to user.id.toString(),
                "log" to logList
            )
        )
    }
}