package com.example.cityworkout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.feature_list_workout.list.view.OnListItemClickListener
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class MainActivity : AppCompatActivity(),
    OnListItemClickListener {
    private val navigator = SupportAppNavigator(this, R.id.content_container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SampleApplication.INSTANCE.getRouter().navigateTo(Screens.Companion.StartWorkoulListScreen())
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.content_container, fragment)
            .addToBackStack("")
            .commit()
    }

    override fun onItemClicked(numberExercise: Int) {
        SampleApplication.INSTANCE.getRouter().navigateTo(Screens.Companion.StartWorkoutDetailScreen(numberExercise))
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        SampleApplication.INSTANCE.getNavigatorHolder().setNavigator(navigator)
    }

    override fun onPause() {
        SampleApplication.INSTANCE.getNavigatorHolder().removeNavigator()
        super.onPause()
    }
}
