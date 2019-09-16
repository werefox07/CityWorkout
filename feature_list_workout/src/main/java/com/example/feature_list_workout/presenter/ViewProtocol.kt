package com.example.feature_list_workout.presenter

import com.arellomobile.mvp.MvpView
import com.example.domain.Workout

interface ViewProtocol: MvpView {
    fun showWorkoutList(workoutList: ArrayList<Workout>)
}