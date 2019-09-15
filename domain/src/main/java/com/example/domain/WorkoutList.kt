package com.example.domain

import java.util.*
import kotlin.collections.ArrayList

class WorkoutList private constructor() {
    private val workouts = arrayListOf<Workout>()

    companion object {
        private val ourInstance = WorkoutList()

        @Synchronized
        fun getInstance() : WorkoutList {
            return ourInstance
        }
    }

    init {
        val random = Random()
        for (i in 0..999) {
            val workout = Workout("Exercise №" + (i+1))
            workout.description = "Description exercise №" + (i+1)
            workout.recordDate = Date()
            workout.recordRepsCount = random.nextInt(101)
            workout.recordWeight = random.nextInt(101)
            workouts.add(workout)
        }
    }

    fun getWorkouts(): ArrayList<Workout> {
        return workouts
    }
}