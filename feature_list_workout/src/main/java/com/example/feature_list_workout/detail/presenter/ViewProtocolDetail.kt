package com.example.feature_list_workout.detail.presenter

import com.arellomobile.mvp.MvpView
import com.example.domain.Workout

interface ViewProtocolDetail : MvpView {
    fun showDetail(workout: Workout)
    fun updateDescription(desc: Int)
}