package com.usnine.prescriptionapp.ui.screen.scan

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.usnine.prescriptionapp.R
import com.usnine.prescriptionapp.ui.components.HorizontalSpacer
import com.usnine.prescriptionapp.ui.components.SectionCard
import com.usnine.prescriptionapp.ui.components.Text
import com.usnine.prescriptionapp.ui.components.TextType
import com.usnine.prescriptionapp.ui.components.VerticalSpacer
import com.usnine.prescriptionapp.ui.theme.Red

@Composable
fun ScanErrorScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 24.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        ErrorHeader()
        ErrorSection()
    }
}

@Composable
private fun ErrorHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Red)
            .padding(vertical = 32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(R.drawable.analysis_error),
                contentDescription = null,
                modifier = Modifier.size(64.dp)
            )
            VerticalSpacer()
            Text(stringResource(R.string.scan_analysis_error), TextType.BODY_LARGE, color = Color.White)
            VerticalSpacer(10.dp)
            Text(stringResource(R.string.scan_analysis_error_hint), TextType.HINT_LARGE, color = Color.White)
        }
    }
}

@Composable
private fun ErrorSection() {
    SectionCard(stringResource(R.string.scan_error_title)) {
        ErrorItem(stringResource(R.string.scan_error_item_1))
        ErrorItem(stringResource(R.string.scan_error_item_2))
        ErrorItem(stringResource(R.string.scan_error_item_3))
    }
}

@Composable
private fun ErrorItem(text: String) {
    Row(
        modifier = Modifier.padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(42.dp),
            painter = painterResource(R.drawable.question),
            contentDescription = null,
        )
        HorizontalSpacer()
        Column(modifier = Modifier.weight(1f)) {
            Text(text, TextType.BODY_MEDIUM, textAlign = TextAlign.Start)
        }
    }
}
