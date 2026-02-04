package com.usnine.prescriptionapp.ui.screen.scan

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.usnine.prescriptionapp.R
import com.usnine.prescriptionapp.data.model.Appointment
import com.usnine.prescriptionapp.data.model.CareSchedule
import com.usnine.prescriptionapp.data.model.Medication
import com.usnine.prescriptionapp.data.model.PrescriptionResponse
import com.usnine.prescriptionapp.ui.components.HorizontalSpacer
import com.usnine.prescriptionapp.ui.components.SectionCard
import com.usnine.prescriptionapp.ui.components.Text
import com.usnine.prescriptionapp.ui.components.TextType
import com.usnine.prescriptionapp.ui.components.VerticalSpacer
import com.usnine.prescriptionapp.ui.theme.Blue
import com.usnine.prescriptionapp.ui.theme.Green
import com.usnine.prescriptionapp.ui.theme.HintGray

@Composable
fun ScanResultScreen(
    result: PrescriptionResponse
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 24.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        ResultHeader(result.documentLabel)
        if (result.hospital != null || result.patient != null || result.date != null || result.treatment != null) {
            InfoSection(result)
        }
        result.medications?.takeIf { it.isNotEmpty() }?.let { MedicationSection(it) }
        result.careSchedules?.takeIf { it.isNotEmpty() }?.let { CareScheduleSection(it) }
        result.appointments?.takeIf { it.isNotEmpty() }?.let { AppointmentSection(it) }
        result.memo?.takeIf { it.isNotEmpty() }?.let { MemoSection(it) }
    }
}

@Composable
fun ResultHeader(label: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Green)
            .padding(vertical = 32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(R.drawable.analysis_success),
                contentDescription = null,
                modifier = Modifier.size(64.dp)
            )
            VerticalSpacer()
            Text(stringResource(R.string.scan_analysis_success), TextType.BODY_LARGE, color = Color.White)
            VerticalSpacer(10.dp)
            Text(stringResource(R.string.scan_analysis_success_hint, label), TextType.HINT_LARGE, color = Color.White)
        }
    }
}

@Composable
fun InfoSection(result: PrescriptionResponse) {
    SectionCard(
        stringResource(R.string.scan_info_title),
        null,
        HintGray,
    ) {
        result.hospital?.let {
            InfoRow(stringResource(R.string.scan_info_hospital), value = it)
        }
        result.patient?.let {
            InfoRow(label = stringResource(R.string.scan_info_patient), value = it)
        }
        result.date?.let {
            InfoRow(label = stringResource(R.string.scan_info_date), value = it)
        }
        result.treatment?.let {
            InfoRow(label = stringResource(R.string.scan_info_treatment), value = it)
        }
    }
}

@Composable
private fun MedicationSection(medications: List<Medication>) {
    SectionCard(
        title = stringResource(R.string.scan_medication_title),
        hint = stringResource(R.string.scan_medication_hint)
    ) {
        medications.forEach { med ->
            val title = med.name ?: stringResource(R.string.scan_medication_item_default_title)
            val frequency = med.frequency?.extractLeadingNumber()
            val dosage = med.dosage?.extractLeadingNumber()
            val hint = if (dosage != null && frequency != null) {
                stringResource(R.string.scan_medication_item_hint, frequency, dosage)
            } else {
                stringResource(R.string.scan_medication_item_default_hint)
            }
            val duration = med.durationDays?.let { stringResource(R.string.scan_medication_item_duration, it) }
            ResultItem(R.drawable.medicine, title, hint, duration)
        }
    }
}

@Composable
private fun CareScheduleSection(careSchedules: List<CareSchedule>) {
    SectionCard(stringResource(R.string.scan_care_title)) {
        careSchedules.forEach { care ->
            val title = care.instruction ?: stringResource(R.string.scan_care_item_default_title)
            val startDay = care.startDay ?: 0
            val hint = stringResource(R.string.scan_care_item_hint, "${startDay + 1}", "${care.endDay ?: "?"}")
            ResultItem(R.drawable.exclamation, title, hint)
        }
    }
}

@Composable
private fun AppointmentSection(appointments: List<Appointment>) {
    SectionCard(title = stringResource(R.string.scan_appointment_title)) {
        appointments.forEach { appointment ->
            val title = appointment.description ?: ""
            val dayInfo = appointment.daysAfter?.let { stringResource(R.string.scan_appointment_item_day_info, it) }
            ResultItem(R.drawable.exclamation, title, "", dayInfo)
        }
    }
}

@Composable
private fun MemoSection(memo: String) {
    SectionCard(title = stringResource(R.string.scan_memo_title)) {
        Text(text = memo, TextType.BODY_SMALL, textAlign = TextAlign.Start)
    }
}


@Composable
fun InfoRow(label: String, value: String, showBottomDivider: Boolean = true) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, TextType.BODY_MEDIUM, color = HintGray)
        Text(value, TextType.BODY_MEDIUM)
    }
    if (showBottomDivider) {
        HorizontalDivider()
    }
}

@Composable
private fun ResultItem(
    @DrawableRes imageRes: Int,
    title: String,
    hint: String,
    dayInfo: String? = null
) {
    Row(
        modifier = Modifier.padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(42.dp),
            painter = painterResource(imageRes),
            contentDescription = null,
        )
        HorizontalSpacer()
        Column(modifier = Modifier.weight(1f)) {
            Text(title, TextType.BODY_MEDIUM, textAlign = TextAlign.Start)
            if (hint.isNotEmpty()) {
                Spacer(Modifier.height(12.dp))
                Text(hint, TextType.HINT_MEDIUM)
            }
        }
        HorizontalSpacer()
        dayInfo?.let { Text(it, TextType.TITLE_SMALL, color = Blue) }


    }
}

private fun String?.extractLeadingNumber(): Int? {
    return this?.takeWhile { it.isDigit() }?.toIntOrNull()
}