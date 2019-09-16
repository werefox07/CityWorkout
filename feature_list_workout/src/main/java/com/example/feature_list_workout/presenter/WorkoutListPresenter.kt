package com.example.feature_list_workout.presenter

import com.example.domain.Workout
import com.example.domain.WorkoutList

class WorkoutListPresenter private constructor() {
    companion object {
        private val instance = WorkoutListPresenter()

        @Synchronized
        fun getInstance(): WorkoutListPresenter {
            return instance
        }
    }

    private var viewProtocol: ViewProtocol? = null
    private val workoutList = getWorkoutList()

    fun attachView(view: ViewProtocol) {
        viewProtocol = view
        showWorkoutList()
    }

    private fun showWorkoutList() {
        viewProtocol?.showWorkoutList(workoutList)
    }

    private fun getWorkoutList(): ArrayList<Workout> {
        return WorkoutList.getInstance().getWorkouts()
    }
}