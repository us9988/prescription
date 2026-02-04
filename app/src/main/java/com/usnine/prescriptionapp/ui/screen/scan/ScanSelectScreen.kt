package com.usnine.prescriptionapp.ui.screen.scan

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.usnine.prescriptionapp.R
import com.usnine.prescriptionapp.ui.components.OptionCard
import com.usnine.prescriptionapp.ui.components.Text
import com.usnine.prescriptionapp.ui.components.TextType

@Composable
fun ScanSelectScreen(
    onClickCamera: () -> Unit,
    onClickAlbum: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            stringResource(R.string.scan_select_header),
            TextType.HEADER,
            Modifier.align(Alignment.Start)
        )
        Spacer(Modifier.height(40.dp))
        Image(
            painter = painterResource(R.drawable.document),
            contentDescription = null,
            modifier = Modifier.size(100.dp).padding(12.dp)
        )
        Spacer(Modifier.height(40.dp))
        Text(stringResource(R.string.scan_select_title), TextType.BODY_LARGE)
        Spacer(Modifier.height(12.dp))
        Text(stringResource(R.string.scan_select_hint), TextType.HINT_LARGE, modifier = Modifier.wrapContentWidth())
        Spacer(Modifier.height(40.dp))
        // 카메라 옵션 카드
        OptionCard(
            R.drawable.camera,
            stringResource(R.string.scan_select_camera_title),
            stringResource(R.string.scan_select_camera_hint),
            onClickCamera
        )
        // 앨범 옵션 카드
        OptionCard(
            R.drawable.album,
            stringResource(R.string.scan_select_album_title),
            stringResource(R.string.scan_select_album_hint),
            onClickAlbum
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ScanSelectScreenPreview() {
//    ScanSelectScreen()
}