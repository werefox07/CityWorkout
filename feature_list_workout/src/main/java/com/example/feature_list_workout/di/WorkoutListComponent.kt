package com.example.feature_list_workout.di

import com.example.feature_list_workout.list.view.WorkoutListFragment
import dagger.Component
@WorkoutListPresenterScope
@Component(modules = [WorkoutListModule::class], dependencies = [WorkoutListDependencies::class])
interface WorkoutListComponent {
    fun inject(fragment: WorkoutListFragment)
}