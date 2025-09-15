package com.keylogs.musicapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.keylogs.musicapp.ui.screen.AlbumScreen
import com.keylogs.musicapp.ui.screen.ArtistsScreen
import com.keylogs.musicapp.ui.screen.SongsScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: Destination,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController,
        startDestination = startDestination.route,
    ) {
        Destination.entries.forEach { destination ->
            composable(destination.route) {
                when (destination) {
                    Destination.SONGS -> SongsScreen()
                    Destination.ALBUM -> AlbumScreen()
                    Destination.ARTISTS -> ArtistsScreen()
                }
            }
        }
    }
}