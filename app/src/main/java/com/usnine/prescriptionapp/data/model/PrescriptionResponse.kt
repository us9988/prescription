package com.usnine.prescriptionapp.data.model

import com.google.gson.annotations.SerializedName

data class PrescriptionResponse(
    /**
     * 0 = 처방전, 1 = 시술 안내문, 2 = 검사 안내문, 3 = 기타
     */
    @SerializedName("document_type")
    val documentType: Int,
    @SerializedName("document_label")
    val documentLabel: String,
    val hospital: String?,
    val patient: String?,
    val date: String?,
    val treatment: String?,
    val medications: List<Medication>?,
    @SerializedName("care_schedule")
    val careSchedules: List<CareSchedule>?,
    val appointments: List<Appointment>?,
    @SerializedName("notes")
    val memo: String?
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
    /**
     * 0 = high, 1 = medium, 2 = low
     */
    val priority: Int?,
    @SerializedName("reminder_times")
    val reminderTimes: List<String>?
)

data class Appointment(
    val description: String?,
    @SerializedName("days_after")
    val daysAfter: Int?
)