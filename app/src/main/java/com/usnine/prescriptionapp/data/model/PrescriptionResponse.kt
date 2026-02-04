package com.usnine.prescriptionapp.data.model

import com.google.gson.annotations.SerializedName

data class PrescriptionResponse(
    @SerializedName("document_type")
    val documentType: String?,
    val hospital: String?,
    val patient: String?,
    val date: String?,
    val treatment: String?,
    val medications: List<Medication>?,
    @SerializedName("care_schedule")
    val careSchedule: List<CareSchedule>?,
    val appointments: List<Appointment>?,
    val notes: String?
)

data class Medication(
    val name: String?,
    val dosage: String?,
    val frequency: String?,
    @SerializedName("duration_days")
    val durationDays: Int?,
    val instructions: String?
)

data class CareSchedule(
    val instruction: String?,
    @SerializedName("start_day")
    val startDay: Int?,
    @SerializedName("end_day")
    val endDay: Int?,
    val priority: String?,
    @SerializedName("reminder_times")
    val reminderTimes: List<String>?
)

data class Appointment(
    val description: String?,
    @SerializedName("days_after")
    val daysAfter: Int?
)