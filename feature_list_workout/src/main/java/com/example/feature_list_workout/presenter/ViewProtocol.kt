package com.example.feature_list_workout.presenter

import com.example.domain.Workout

interface ViewProtocol {
    fun showWorkoutList(workoutList: ArrayList<Workout>)
}