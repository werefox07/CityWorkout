package com.example.cityworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.feature_list_workout.view.OnListItemClickListener
import com.example.feature_list_workout.view.WorkoutListFragment

class MainActivity : AppCompatActivity(),
    OnListItemClickListener {
    private lateinit var workoutListFragment : WorkoutListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        workoutListFragment = WorkoutListFragment()
        if (savedInstanceState == null) {
            setFragment(workoutListFragment)
        }
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.content_container, fragment)
            .addToBackStack("")
            .commit()
    }

    override fun onItemClicked(index: Int) {
        Log.e("TO IMPLEMENT", "Здесь будет обработка")
    }
}
