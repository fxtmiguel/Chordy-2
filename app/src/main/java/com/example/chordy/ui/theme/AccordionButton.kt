package com.example.chordy.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

//circle shaped button
@Composable
fun AccordionButton(
    note: String,
    chordNotes: Set<String>,
    pressedNotes: Set<String>,
    onPress: (String) -> Unit
) {
    val isChordNote = note in chordNotes
    //buttons that need to change their opacity to 100%
    val isPressed = note in pressedNotes

    Box(
        modifier = Modifier
            .size(buttonSize)
            .background(
                when {
                    isPressed -> Color(0xFF3ABF7F) // solid green when pressed
                    isChordNote -> Color(0xFF3ABF7F).copy(alpha = 0.4f) // light green for chord notes
                    else -> Color.White
                },
                CircleShape
            )
            .border(2.dp, Color.Black, CircleShape)
            .clickable {
                if (isChordNote) {
                    onPress(note)
                }
            },
        contentAlignment = Alignment.Center
    ) {
        //shows the notes
        Text(note)
    }
}
