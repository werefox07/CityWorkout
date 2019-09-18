package com.example.feature_list_workout.list.presenter

import com.arellomobile.mvp.MvpView
import com.example.domain.Workout

interface ViewProtocolList: MvpView {
    fun showWorkoutList(workoutList: ArrayList<Workout>)
}