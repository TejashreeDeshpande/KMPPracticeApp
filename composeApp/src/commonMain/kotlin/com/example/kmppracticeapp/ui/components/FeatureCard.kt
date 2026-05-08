package com.example.kmppracticeapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.kmppracticeapp.models.DriveFeature

@Composable
fun FeatureCard(
    feature: DriveFeature,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .aspectRatio(1f)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = getIconForName(feature.icon),
                contentDescription = feature.name,
                modifier = Modifier.size(48.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = feature.name,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

private fun getIconForName(name: String): ImageVector {
    return when (name) {
        "location" -> Icons.Default.Home
        "play" -> Icons.Default.PlayArrow
        "call" -> Icons.Default.Notifications
        "email" -> Icons.Default.Email
        "apps" -> Icons.Default.List
        "settings" -> Icons.Default.Settings
        else -> Icons.Default.Info
    }
}
