package com.usnine.prescriptionapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.usnine.prescriptionapp.ui.theme.Blue

@Composable
fun CircularProgress(
    bodyRes: Int? = null,
    hintRes: Int? = null,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = modifier.size(64.dp),
            color = Blue,
            strokeWidth = 4.dp
        )
        if (bodyRes != null) {
            Spacer(modifier.height(20.dp))
            Text(stringResource(bodyRes), TextType.BODY_MEDIUM)
            if (hintRes != null) {
                Spacer(modifier.height(20.dp))
                Text(stringResource(hintRes), TextType.HINT_MEDIUM)
            }
        }
    }

}

@Preview
@Composable
fun ProgressPreview() {
    CircularProgress()
}