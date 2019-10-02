package com.example.feature_list_workout.detail.view

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.domain.Workout
import com.example.feature_list_workout.R
import com.example.feature_list_workout.detail.presenter.*
import java.security.AccessController.getContext
import kotlinx.android.synthetic.main.layout_workout_detail.*

class WorkoutDetailFragment: MvpAppCompatFragment(), ViewProtocolWorkoutDetailScreen {

    @InjectPresenter
    lateinit var presenter: WorkoutDetailPresenter
    var numberExercise: Int? = 0
    val WORKOUT_INDEX = "workoutIndex"

    companion object{
        fun getNewInstance(numberExercise: Int): WorkoutDetailFragment {
            val fragment = WorkoutDetailFragment()
            val arguments = Bundle()
            arguments.putInt(fragment.WORKOUT_INDEX, numberExercise)
            fragment.arguments = arguments
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.layout_workout_detail, container, false)
        numberExercise = getArguments()?.getInt(WORKOUT_INDEX)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setListeners()
    }

    private fun setValues(workout: Workout) {
        workoutDetailRecordDate.text = workout.getFormattedRecordDate()
        workoutDetailRecordRepsCount.text = workout.recordRepsCount.toString()
        workoutDetailRecordWeight.text = workout.recordWeight.toString()
        workoutDetailTitle.text = getContext()?.getString(R.string.title_exercise) + numberExercise.toString()
    }

    override fun showDetail(workout: Workout) {
        setValues(workout)
    }

    private fun setListeners() {
        shareButton.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            presenter.downloadDescriptionExercise()
        }

        convert_button.setOnClickListener() {
            progressBarConvertImage.visibility = View.VISIBLE
            presenter.convertImageToPng()
        }
    }

    override fun updateDescription(desc: Int) {
        progressBar.visibility = View.INVISIBLE
        descriptionExersice.text = desc.toString()
    }

    override fun updateInfoConvert(text: String) {
        progressBarConvertImage.visibility = View.INVISIBLE
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show()
    }
}