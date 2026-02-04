package com.usnine.prescriptionapp.ui.screen.scan

import android.net.Uri

data class ScanState(
    val imageUri: Uri? = null,
    val phase: ScanPhase = ScanPhase.Idle,
    val analysisResult: String? = null,
    val errorMessage: String? = null
)

enum class ScanPhase {
    Idle,
    ImageReady,
    Analyzing,
    Result
}
