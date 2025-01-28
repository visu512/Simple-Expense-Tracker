package com.appexpensetrackers.statics

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.appexensetrackers.R

@Composable
fun StatsScreen(navController: NavHostController) {
    // Displaying a full-size image
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.statistic),
            contentDescription = "Graph",
            modifier = Modifier.fillMaxSize(), // Ensures the image fills the screen
            contentScale = ContentScale.Fit  // Adjust scaling while keeping the aspect ratio
        )
    }
}
