package com.example.buved.presentation.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun BadgedBoxIcon(badgeCount: String, onNavigate: () -> Unit, icon: ImageVector, iconDescription: String) {
    BadgedBox(
        badge = {
            Badge { Text(text = badgeCount) }
        },
        modifier = Modifier
            .padding(vertical = 8.dp)
            .padding(end = 16.dp)
    ) {
        IconButton(onClick = onNavigate) {
            Icon(imageVector = icon, contentDescription = iconDescription)
        }
    }
}