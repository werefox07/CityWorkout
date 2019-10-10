package com.example.feature_list_workout.di

import com.example.feature_list_workout.list.view.OnListItemClickListener
import com.example.feature_list_workout.list.view.WorkoutAdapter
import dagger.Module
import dagger.Provides

@Module
class WorkoutListModule (private val itemClickListener: OnListItemClickListener) {
    @Provides
    @WorkoutListPresenterScope
    fun provideAdapter(): WorkoutAdapter {
        return WorkoutAdapter(itemClickListener)
    }
}