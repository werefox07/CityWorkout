package com.example.feature_list_workout.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.Workout
import com.example.feature_list_workout.R
import com.example.feature_list_workout.presenter.ViewProtocol
import com.example.feature_list_workout.presenter.WorkoutListPresenter

class WorkoutListFragment : Fragment(), ViewProtocol {
    private lateinit var listener: OnListItemClickListener
    private lateinit var adapter: WorkoutAdapter
    private val presenter = WorkoutListPresenter.getInstance()

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.layout_workout_list, container, false)
        val recyclerView = root.findViewById<RecyclerView>(R.id.recycler_view)
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        adapter = WorkoutAdapter(listener)
        recyclerView.adapter = adapter
        return root
    }

    override fun onAttach(context: Context) {
        if (context is OnListItemClickListener) {
            listener = context
        }
        super.onAttach(context)
    }

    override fun showWorkoutList(workoutList: ArrayList<Workout>) {
        adapter.swapUI(workoutList)
    }
}