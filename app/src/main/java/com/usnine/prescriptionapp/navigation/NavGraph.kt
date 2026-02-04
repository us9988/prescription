package com.usnine.prescriptionapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.usnine.prescriptionapp.ui.screen.history.HistoryScreen
import com.usnine.prescriptionapp.ui.screen.home.HomeScreen
import com.usnine.prescriptionapp.ui.screen.profile.ProfileScreen
import com.usnine.prescriptionapp.ui.screen.scan.ScanScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Home.route,
        modifier = modifier
    ) {
        composable(BottomNavItem.Home.route) { HomeScreen() }
        composable(BottomNavItem.Scan.route) { ScanScreen() }
        composable(BottomNavItem.History.route) { HistoryScreen() }
        composable(BottomNavItem.Profile.route) { ProfileScreen() }
    }
}
