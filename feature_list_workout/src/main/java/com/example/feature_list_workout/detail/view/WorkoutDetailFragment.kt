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

class WorkoutDetailFragment : MvpAppCompatFragment(), ViewProtocolWorkoutDetailScreen {

    @InjectPresenter
    lateinit var presenter: WorkoutDetailPresenter

    lateinit var recordDate: TextView
    lateinit var recordRepsCount: TextView
    lateinit var recordWeight: TextView
    lateinit var weight: TextView
    lateinit var weightSeekBar: SeekBar
    lateinit var repsCountEditText: EditText
    lateinit var saveRecordButton: Button
    lateinit var downloadButton: Button
    lateinit var titleExercise: TextView
    lateinit var progressBar: ProgressBar
    lateinit var descriptionExercise: TextView
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
        initUI(root)
        setListeners()
        return root
    }

    private fun setValues(workout: Workout) {
        recordDate.text = workout.getFormattedRecordDate()
        recordRepsCount.text = workout.recordRepsCount.toString()
        recordWeight.text = workout.recordWeight.toString()
    }

    private fun initUI(view: View) {
        titleExercise = view.findViewById(R.id.workout_detail_title)
        recordDate = view.findViewById(R.id.workout_detail_record_date)
        recordRepsCount = view.findViewById(R.id.workout_detail_record_reps_count)
        recordWeight = view.findViewById(R.id.workout_detail_record_weight)
        weight = view.findViewById(R.id.workout_detail_weight)
        weightSeekBar = view.findViewById(R.id.workout_detail_seek_bar)
        repsCountEditText = view.findViewById(R.id.workout_detail_reps_count_edit_text)
        saveRecordButton = view.findViewById(R.id.workout_detail_save_button)
        downloadButton = view.findViewById(R.id.share_button)
        progressBar = view.findViewById(R.id.progress_bar)
        descriptionExercise = view.findViewById(R.id.description_exersice)
    }

    override fun showDetail(workout: Workout) {
        setValues(workout)
    }

    private fun setListeners() {
        downloadButton.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            presenter.downloadDescriptionExercise()
        }
    }

    override fun updateDescription(desc: Int) {
        progressBar.visibility = View.INVISIBLE
        descriptionExercise.text = desc.toString()
    }
}