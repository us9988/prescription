package com.usnine.prescriptionapp.ui.screen.scan

import android.net.Uri
import com.usnine.prescriptionapp.data.model.PrescriptionResponse

data class ScanState(
    val imageUri: Uri? = null,
    val phase: ScanPhase = ScanPhase.Idle,
    val analysisResult: PrescriptionResponse? = null,
    val errorMessage: String? = null
)

enum class ScanPhase {
    Idle,
    ImageReady,
    Analyzing,
    Result
}
