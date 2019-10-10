package com.example.feature_list_workout.di

import com.example.feature_list_workout.list.view.OnListItemClickListener

interface WorkoutListDependencies {
    fun funGetOnListItemClickListener(): OnListItemClickListener
}