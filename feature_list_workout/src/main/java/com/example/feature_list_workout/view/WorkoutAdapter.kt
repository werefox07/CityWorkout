package com.example.feature_list_workout.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.Workout
import com.example.feature_list_workout.R
import java.util.ArrayList

class WorkoutAdapter(private val itemClickListener: OnListItemClickListener) :
    RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder>() {
    private var workoutList = arrayListOf<Workout>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        WorkoutViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item,
                parent,
                false
            )
        )

    override fun getItemCount(): Int {
        return workoutList.size
    }

    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
        holder.bindView(workoutList[position], position, itemClickListener)
    }

    inner class WorkoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title = itemView.findViewById<TextView>(R.id.list_item_title_text_view)
        private val description =
            itemView.findViewById<TextView>(R.id.list_item_description_text_view)
        private val recordDate = itemView.findViewById<TextView>(R.id.list_item_record_date)
        private val recordRepsCount =
            itemView.findViewById<TextView>(R.id.list_item_record_reps_count)
        private val recordWeight = itemView.findViewById<TextView>(R.id.list_item_record_weight)
        private val cardView = itemView.findViewById<CardView>(R.id.cardView)

        fun bindView(workout: Workout, index: Int, listener: OnListItemClickListener) {
            title.text = workout.title
            description.text = workout.description
            recordWeight.text = workout.recordWeight.toString()
            recordRepsCount.text = workout.recordRepsCount.toString()
            recordDate.text = workout.getFormattedRecordDate()
            cardView.setOnClickListener {
                listener.onItemClicked(index)
            }
        }
    }

    fun swapUI(workoutList: ArrayList<Workout>) {
        this.workoutList.clear()
        this.workoutList.addAll(workoutList)
    }
}