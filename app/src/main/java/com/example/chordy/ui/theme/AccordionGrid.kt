package com.example.chordy.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.unit.Dp

// 34-button layout
val treble34Layout = listOf(
    listOf("C#", "G", "A#", "D", "E", "G", "A#", "D", "E", "G", "A#"), // Column 1 (11)
    listOf("F#", "A", "C", "D#", "G", "A", "C", "D#", "G", "A", "C", "D#"), // Column 2 (12)
    listOf("B", "D", "F", "G#", "C", "D", "F", "G#", "C", "D", "F") // Column 3 (11)
)

//the notes that make the chords
val interactiveChordButtonMap = mapOf(
    "G" to setOf("G", "B", "D"),
    "F" to setOf("F", "A", "C"),
    "Bb" to setOf("A#", "D", "F"),
    "Eb" to setOf("D#", "G", "A#")
)


val buttonSize = 40.5.dp
private val buttonSpacing = 6.dp
//offset for the middle row
private val middleColumnOffset: Dp = (-12).dp

@Composable
fun AccordionTreble34Interactive(selectedChord: String) {
    //check reference the notes for the chord
    val chordNotes = chordButtonMap[selectedChord] ?: emptySet()
    var pressedNotes by remember(selectedChord) { mutableStateOf(setOf<String>()) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top
    ) {
        // Left column
        Column(
            verticalArrangement = Arrangement.spacedBy(buttonSpacing),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            treble34Layout[0].forEach { note ->
                AccordionButton(note, chordNotes, pressedNotes) { pressedNotes += it }
            }
        }

        Spacer(modifier = Modifier.width(20.dp))

        // Middle column (longer column)
        Column(
            verticalArrangement = Arrangement.spacedBy(buttonSpacing),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.offset(y = middleColumnOffset)
        ) {
            treble34Layout[1].forEach { note ->
                AccordionButton(note, chordNotes, pressedNotes) { pressedNotes += it }
            }
        }

        Spacer(modifier = Modifier.width(20.dp))

        // Right column
        Column(
            verticalArrangement = Arrangement.spacedBy(buttonSpacing),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            treble34Layout[2].forEach { note ->
                AccordionButton(note, chordNotes, pressedNotes) { pressedNotes += it }
            }
        }
    }
}

@Composable
fun AccordionButtonInteractive(
    note: String,
    chordNotes: Set<String>,
    pressedNotes: Set<String>,
    onPress: (String) -> Unit
) {
    val isChordNote = note in chordNotes
    val isPressed = note in pressedNotes

    Box(
        modifier = Modifier
            .size(buttonSize)
            .background(
                when {
                    isPressed -> Color(0xFF3ABF7F)
                    isChordNote -> Color(0xFF3ABF7F).copy(alpha = 0.4f)
                    else -> Color.White
                },
                CircleShape
            )
            .border(2.dp, Color.Black, CircleShape)
            .clickable {
                if (isChordNote) onPress(note)
            },
        contentAlignment = Alignment.Center
    ) {
        Text(note)
    }
}
