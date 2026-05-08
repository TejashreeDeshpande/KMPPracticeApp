package com.example.kmppracticeapp.mvi

import com.example.kmppracticeapp.models.DriveFeature

data class DriveState(
    val features: List<DriveFeature> = emptyList(),
    val currentSpeed: Int = 0,
    val fuelLevel: String = "85%",
    val range: String = "420 km",
    val temperature: String = "22°C",
    val isLoading: Boolean = false,
    val error: String? = null
)

sealed class DriveIntent {
    object LoadFeatures : DriveIntent()
    data class UpdateSpeed(val speed: Int) : DriveIntent()
    data class FeatureClicked(val feature: DriveFeature) : DriveIntent()
}

sealed class DriveEffect {
    data class ShowToast(val message: String) : DriveEffect()
    data class NavigateToFeature(val featureId: String) : DriveEffect()
}
