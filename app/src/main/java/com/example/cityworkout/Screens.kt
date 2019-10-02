package com.example.cityworkout

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import com.example.feature_list_workout.detail.view.WorkoutDetailFragment
import com.example.feature_list_workout.list.view.WorkoutListFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens{
    companion object {
        class StartWorkoulListScreen() : SupportAppScreen() {
            override fun getFragment(): Fragment {
                return WorkoutListFragment.getNewInstance()
            }
        }

        class StartWorkoutDetailScreen(var numberExercise: Int): SupportAppScreen() {
            override fun getFragment(): Fragment {
                return WorkoutDetailFragment.getNewInstance(numberExercise)
            }
        }

        class StartMainScreen : SupportAppScreen() {
            override fun getActivityIntent(context: Context?): Intent {
                return Intent(context, MainActivity::class.java)
            }
        }
    }
}


