package com.example.feature_list_workout.detail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.domain.Workout
import com.example.feature_list_workout.R
import com.example.feature_list_workout.detail.presenter.ViewProtocolWorkoutDetailScreen
import com.example.feature_list_workout.detail.presenter.WorkoutDetailPresenter
import java.security.AccessController.getContext
import kotlinx.android.synthetic.main.layout_workout_detail.*

class WorkoutDetailFragment : MvpAppCompatFragment(), ViewProtocolWorkoutDetailScreen {

    @InjectPresenter
    lateinit var presenter: WorkoutDetailPresenter
    private var numberExersice = -1

    fun passNumberExercice(workoutIndex: Int) {
        numberExersice = workoutIndex
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.layout_workout_detail, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setListeners()
    }

    private fun setValues(workout: Workout) {
        workout_detail_record_date.text = workout.getFormattedRecordDate()
        workout_detail_record_reps_count.text = workout.recordRepsCount.toString()
        workout_detail_record_weight.text = workout.recordWeight.toString()
    }

    override fun showDetail(workout: Workout) {
        setValues(workout)
    }

    private fun setListeners() {
        share_button.setOnClickListener {
            progress_bar.visibility = View.VISIBLE
            presenter.downloadDescriptionExercise()
        }

        convert_button.setOnClickListener() {
            progress_bar_convert_image.visibility = View.VISIBLE
            presenter.convertImageToPng()
        }
    }

    override fun updateDescription(desc: Int) {
        progress_bar.visibility = View.INVISIBLE
        description_exersice.text = desc.toString()
    }

    override fun updateInfoConvert(text: String) {
        progress_bar_convert_image.visibility = View.INVISIBLE
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show()
    }
}