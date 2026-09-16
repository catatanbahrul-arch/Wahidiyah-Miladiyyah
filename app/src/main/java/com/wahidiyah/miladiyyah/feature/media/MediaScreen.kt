package com.wahidiyah.miladiyyah.feature.media

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MediaScreen(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier.fillMaxSize().padding(paddingValues).padding(20.dp)
    ) {
        Text("Media")
        Text(
            "Video/live dan konten remote akan masuk pada tahap integrasi API.",
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}
