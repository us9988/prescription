package com.usnine.prescriptionapp.ui.screen.scan

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.usnine.prescriptionapp.data.repository.PrescriptionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScanViewModel @Inject constructor(
    private val repository: PrescriptionRepository
) : ViewModel() {

    companion object {
        private const val TAG = "ScanViewModel"
    }

    private val _state = MutableStateFlow(ScanState())
    val state: StateFlow<ScanState> = _state.asStateFlow()

    fun onIntent(intent: ScanIntent) {
        when (intent) {
            is ScanIntent.TakePhoto -> { /* 카메라 런처는 Screen에서 처리 */
            }
            is ScanIntent.PickFromGallery -> { /* 갤러리 런처는 Screen에서 처리 */
            }
            is ScanIntent.ImageSelected -> {
                _state.update { it.copy(imageUri = intent.uri, phase = ScanPhase.ImageReady, errorMessage = null) }
                analyzeImage(intent.uri)
            }
            is ScanIntent.AnalyzeDocument -> {
                val uri = _state.value.imageUri ?: return
                analyzeImage(uri)
            }
            is ScanIntent.RetryAnalysis -> {
                _state.update { it.copy(phase = ScanPhase.ImageReady, errorMessage = null) }
            }
            is ScanIntent.ResetScan -> {
                _state.update { ScanState() }
            }
        }
    }

    private fun analyzeImage(uri: Uri) {
        viewModelScope.launch {
            _state.update { it.copy(phase = ScanPhase.Analyzing, errorMessage = null) }
            val result = repository.analyzePrescription(uri)
            result.onSuccess { response ->
                Log.d(TAG, "분석 성공: $response")
                _state.update { it.copy(phase = ScanPhase.Result, analysisResult = response) }
            }.onFailure { e ->
                Log.e(TAG, "분석 실패", e)
                _state.update { it.copy(phase = ScanPhase.Result, errorMessage = "분석 실패: ${e.message}") }
            }
        }
    }
}
