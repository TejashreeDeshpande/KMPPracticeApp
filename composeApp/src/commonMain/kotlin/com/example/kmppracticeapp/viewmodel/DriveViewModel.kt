package com.example.kmppracticeapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmppracticeapp.models.DriveFeature
import com.example.kmppracticeapp.mvi.DriveEffect
import com.example.kmppracticeapp.mvi.DriveIntent
import com.example.kmppracticeapp.mvi.DriveState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DriveViewModel : ViewModel() {

    private val _state = MutableStateFlow(DriveState())
    val state: StateFlow<DriveState> = _state.asStateFlow()

    private val _effects = Channel<DriveEffect>(Channel.BUFFERED)
    val effects = _effects.receiveAsFlow()

    init {
        handleIntent(DriveIntent.LoadFeatures)
    }

    fun handleIntent(intent: DriveIntent) {
        when (intent) {
            is DriveIntent.LoadFeatures -> loadFeatures()
            is DriveIntent.UpdateSpeed -> updateSpeed(intent.speed)
            is DriveIntent.FeatureClicked -> handleFeatureClick(intent.feature)
        }
    }

    private fun loadFeatures() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            // Simulate network or database delay
            val features = listOf(
                DriveFeature("nav", "Navigation", "location"),
                DriveFeature("music", "Music", "play"),
                DriveFeature("calls", "Phone", "call"),
                DriveFeature("msgs", "Messages", "email"),
                DriveFeature("apps", "Apps", "apps"),
                DriveFeature("settings", "Settings", "settings")
            )
            _state.update { 
                it.copy(
                    features = features,
                    isLoading = false
                ) 
            }
        }
    }

    private fun updateSpeed(speed: Int) {
        _state.update { it.copy(currentSpeed = speed) }
    }

    private fun handleFeatureClick(feature: DriveFeature) {
        viewModelScope.launch {
            _effects.send(DriveEffect.ShowToast("Opening ${feature.name}"))
            _effects.send(DriveEffect.NavigateToFeature(feature.id))
        }
    }
}
