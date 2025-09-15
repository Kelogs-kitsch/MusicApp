package com.keylogs.musicapp.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Album
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.People
import androidx.compose.ui.graphics.vector.ImageVector

enum class Destination(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val contentDescription: String
) {
    SONGS("songs", "Songs", Icons.Filled.MusicNote, "Songs"),
    ALBUM("album", "Album", Icons.Filled.Album, "Album"),
    ARTISTS("artists", "Artists", Icons.Filled.People, "Artists"),
}