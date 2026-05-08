package com.example.kmppracticeapp

import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kmppracticeapp.ui.screens.DriveDashboardScreen
import com.example.kmppracticeapp.ui.theme.DriveModeTheme
import com.example.kmppracticeapp.viewmodel.DriveViewModel
import com.example.kmppracticeapp.mvi.DriveIntent
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    DriveModeTheme {
        val viewModel: DriveViewModel = viewModel { DriveViewModel() }
        
        // Simulate speed changes
        LaunchedEffect(Unit) {
            var speed = 0
            while(true) {
                kotlinx.coroutines.delay(1000)
                speed = (0..120).random()
                viewModel.handleIntent(DriveIntent.UpdateSpeed(speed))
            }
        }

        DriveDashboardScreen(viewModel = viewModel)
    }
}
