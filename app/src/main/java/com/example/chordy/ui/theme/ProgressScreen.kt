package com.example.chordy.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment

//correct notes for quizzes
val quizChords = mapOf(
    "G" to setOf("G", "B", "D"),
    "F" to setOf("F", "A", "C"),
    "Bb" to setOf("A#", "D", "F"),
    "Eb" to setOf("D#", "G", "A#")
)

//options for quiz
val quizOptions = mapOf(
    "G" to listOf("G", "B", "D", "C", "E", "F"),
    "F" to listOf("F", "A", "C", "D", "G", "Bb"),
    "Bb" to listOf("A#", "D", "F", "G", "C", "E"),
    "Eb" to listOf("D#", "G", "A#", "C", "F", "Bb")
)

@Composable
fun ProgressScreen() {
    var quizIndex by remember { mutableStateOf(0) }
    //selected highlighted notes
    var selectedNotes by remember { mutableStateOf(setOf<String>()) }
    var passedQuizzes by remember { mutableStateOf(0) }

    val chordNames = quizChords.keys.toList()
    val currentChord = chordNames[quizIndex]
    val chordNotes = quizChords[currentChord] ?: emptySet()
    val options = quizOptions[currentChord]?.shuffled() ?: emptyList() // Shuffle options each time

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //main title
        Text("Progress Quiz", style = MaterialTheme.typography.headlineMedium)

        LinearProgressIndicator(
            progress = passedQuizzes / 4f,
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
        )
        //current chord name displayed
        Text(
            "Chord: $currentChord",
            fontSize = 28.sp,
            color = MaterialTheme.colorScheme.primary
        )

        Text("Quiz ${quizIndex + 1} of 4", fontSize = 20.sp)

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //options being displayed
            options.chunked(3).forEach { row ->
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    row.forEach { note ->
                        val isSelected = note in selectedNotes
                        //circle buttons options
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .background(
                                    if (isSelected) Color(0xFF3ABF7F) else Color.White,
                                    CircleShape
                                )
                                .border(2.dp, Color.Black, CircleShape)
                                .clickable {
                                    selectedNotes = if (isSelected) selectedNotes - note
                                    else if (selectedNotes.size < 3) selectedNotes + note
                                    else selectedNotes
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(note)
                        }
                    }
                }
            }
        }
        //submit options
        Button(
            onClick = {
                if (selectedNotes == chordNotes) {
                    passedQuizzes++

                    if (quizIndex < 3) quizIndex++
                    selectedNotes = emptySet()
                } else {
                    selectedNotes = emptySet()
                }
            },
            enabled = selectedNotes.size == 3
        ) {
            Text("Submit")
        }
        //
        if (passedQuizzes == 4) {
            Text("🎉 All quizzes completed!", style = MaterialTheme.typography.headlineSmall)
        }
        //reset button
        Button(onClick = {
            passedQuizzes = 0
            quizIndex = 0
            selectedNotes = emptySet()
        }) {
            Text("Reset Progress")
        }
    }
}