package com.example.domain

import java.text.SimpleDateFormat
import java.util.*

class Workout(var title: String) {
    var description: String? = null
    var recordRepsCount: Int = 0
    var recordDate: Date? = null
    var recordWeight: Int = 0

    fun getFormattedRecordDate(): String {
        return SimpleDateFormat("dd MMMM yyyy", Locale.ROOT).format(recordDate)
    }
}