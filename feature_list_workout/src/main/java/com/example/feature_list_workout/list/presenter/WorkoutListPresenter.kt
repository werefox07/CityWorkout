package com.example.feature_list_workout.list.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.domain.Workout
import com.example.domain.WorkoutList

@InjectViewState
class WorkoutListPresenter : MvpPresenter<ViewProtocolWorkoutListScreen>() {
    private val workoutList = getWorkoutList()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        showWorkoutList()
    }

    private fun showWorkoutList() {
        viewState.showWorkoutList(workoutList)
    }

    private fun getWorkoutList(): ArrayList<Workout> {
        return WorkoutList.getInstance().getWorkouts()
    }
}