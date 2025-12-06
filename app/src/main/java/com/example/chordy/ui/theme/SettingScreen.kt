package com.example.chordy.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
@Composable
fun SettingsScreen(
    onBack: () -> Unit
) {
    //fake slider
    var fakeButtonSize by remember { mutableFloatStateOf(37f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //title and icon
        Icon(
            Icons.Default.MusicNote,
            contentDescription = "Music Icon",
            modifier = Modifier.size(80.dp),
            tint = Color.Gray
        )

        Text("Settings", style = MaterialTheme.typography.headlineMedium)

        Text("Accordion Button Size", style = MaterialTheme.typography.titleMedium)
        Slider(
            value = fakeButtonSize,
            onValueChange = { fakeButtonSize = it },
            valueRange = 34f..40.5f
        )
        Text("${fakeButtonSize.toInt()} dp")

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.alpha(0.3f) // low opacity for placeholder
        ) {
            Text("Dark Mode")
            Spacer(modifier = Modifier.width(8.dp))
            Switch(
                checked = false,
                onCheckedChange = {},
                enabled = false
            )
        }

        Spacer(modifier = Modifier.height(50.dp))
        //I did not get to finish features
        Text("More features coming soon!", style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(50.dp))

        Button(onClick = onBack) {
            Text("Back")
        }
    }
}
