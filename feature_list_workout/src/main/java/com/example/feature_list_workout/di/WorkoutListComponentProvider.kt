package com.example.feature_list_workout.di

import com.example.feature_list_workout.list.view.OnListItemClickListener

class WorkoutListComponentProvider {
    lateinit var workoutListComponent: WorkoutListComponent

    companion object {
        var injectionFunction: (WorkoutListComponentProvider.(OnListItemClickListener) -> Unit)? =
            null

        private val instance = WorkoutListComponentProvider()
    }

    fun getInstance(onListItemClickListener: OnListItemClickListener): WorkoutListComponentProvider {
        injectionFunction?.invoke(instance, onListItemClickListener)
        return instance
    }
}
