package com.usnine.prescriptionapp.ui.screen.scan

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.usnine.prescriptionapp.R
import com.usnine.prescriptionapp.ui.components.CircularProgress

@Composable
fun ScanScreen(viewModel: ScanViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        uri?.let { viewModel.onIntent(ScanIntent.ImageSelected(it)) }
    }

    when (state.phase) {
        ScanPhase.Idle -> ScanSelectScreen(
            onClickCamera = { viewModel.onIntent(ScanIntent.TakePhoto) },
            onClickAlbum = {
                photoPickerLauncher.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            }
        )
        ScanPhase.ImageReady -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    state.errorMessage?.let {
                        Text(text = it, color = MaterialTheme.colorScheme.error)
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                    OutlinedButton(onClick = { viewModel.onIntent(ScanIntent.ResetScan) }) {
                        Text("다시 선택하기")
                    }
                }
            }
        }
        ScanPhase.Analyzing -> {
            CircularProgress(
                bodyRes = R.string.scan_analyze_title,
                hintRes = R.string.scan_analyze_hint,
            )
        }
        ScanPhase.Result -> {
            val result = state.analysisResult
            if (result == null || result.documentType == -1) {
                ScanErrorScreen()
            } else {
                Log.e("ScanScreen", "Result: $result")
                ScanResultScreen(result)
            }
        }
    }
}

