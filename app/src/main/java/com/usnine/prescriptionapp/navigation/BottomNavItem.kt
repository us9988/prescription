package com.usnine.prescriptionapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object Home : BottomNavItem("home", "홈", Icons.Default.Home)
    data object Scan : BottomNavItem("scan", "스캔", Icons.Default.CameraAlt)
    data object History : BottomNavItem("history", "기록", Icons.Default.History)
    data object Profile : BottomNavItem("profile", "내 정보", Icons.Default.Person)
}
