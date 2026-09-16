package com.wahidiyah.miladiyyah.feature.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MenuScreen(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier.fillMaxSize().padding(paddingValues).padding(20.dp)
    ) {
        Text("Menu")
        Text(
            "Modul tambahan akan diaktifkan bertahap sesuai data dan kebutuhan aplikasi.",
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}
