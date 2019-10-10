package com.example.feature_list_workout.list.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.domain.Workout
import com.example.feature_list_workout.R
import com.example.feature_list_workout.di.WorkoutListModule
import com.example.feature_list_workout.list.presenter.ViewProtocolWorkoutListScreen
import com.example.feature_list_workout.list.presenter.WorkoutListPresenter
import javax.inject.Inject

class WorkoutListFragment : MvpAppCompatFragment(), ViewProtocolWorkoutListScreen {
    private lateinit var listener: OnListItemClickListener

    @InjectPresenter
    lateinit var presenter: WorkoutListPresenter

    @Inject
    lateinit var adapter: WorkoutAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.layout_workout_list, container, false)
        initDagger()
        val recyclerView = root.findViewById<RecyclerView>(R.id.recycler_view)
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        adapter = WorkoutAdapter(listener)
        recyclerView.adapter = adapter
        return root
    }

    companion object{
        fun getNewInstance(): WorkoutListFragment {
            return WorkoutListFragment()
        }
    }

    private fun initDagger() {
       val component = DaggerWorkoutListComponent.build()
           .workoutListModule(WorkoutListModule.)
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