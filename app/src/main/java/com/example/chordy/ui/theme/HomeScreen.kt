package com.example.chordy.ui.theme

import androidx.compose.material3.*
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.ui.unit.sp
import com.example.chordy.ui.theme.AccordionTreble34Interactive
import com.example.chordy.ui.theme.chordButtonMap
import java.util.Collections.emptySet
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onNavigateToSettings: () -> Unit = {}) {

    //tabs for my chords
    val chordTabs = listOf("G", "F", "Bb", "Eb")
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        //topbar with title and settings
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            Icons.Default.MusicNote,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )

                        Spacer(modifier = Modifier.width(8.dp))


                        Text(
                            "Chordy",
                            style = MaterialTheme.typography.headlineLarge.copy(
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = (-1).sp,
                                shadow = Shadow(
                                    color = Color.Black.copy(alpha = 0.25f),
                                    offset = Offset(2f, 2f),
                                    blurRadius = 8f
                                )
                            ),
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                },

                actions = {
                    IconButton(onClick = onNavigateToSettings) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                    }
                }
            )
        },
        //got rid of the extra padding
        contentWindowInsets = WindowInsets(0, 0, 0, 0)

    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            TabRow(selectedTabIndex = selectedTab) {
                chordTabs.forEachIndexed { index, chord ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = { Text(chord) }
                    )
                }
            }
            //accordion ui
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 0.dp),
                contentAlignment = Alignment.Center
            ) {
                val highlighted = chordButtonMap[chordTabs[selectedTab]] ?: emptySet()
                AccordionTreble34Interactive(selectedChord = chordTabs[selectedTab])
            }
        }
    }
}
