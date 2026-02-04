package com.usnine.prescriptionapp.ui.screen.scan

import android.net.Uri

sealed class ScanIntent {
    data object TakePhoto : ScanIntent()
    data object PickFromGallery : ScanIntent()
    data class ImageSelected(val uri: Uri) : ScanIntent()
    data object AnalyzeDocument : ScanIntent()
    data object RetryAnalysis : ScanIntent()
    data object ResetScan : ScanIntent()
}
