package com.example.feature_dashboard.presentation.composeview

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RetryComposeView(
    onRetryClick: () -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth(1f)
            .fillMaxWidth(1f),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(
            Modifier.fillMaxWidth(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Something Went Wrong", fontSize = 24.sp)
        }
        Column(
            verticalArrangement = Arrangement.Bottom
        ) {
            Button(
                onClick = onRetryClick,
                Modifier
                    .fillMaxWidth(1f)
                    .padding(16.dp)
            ) {
                Text(text = "Retry")
            }
        }
    }
}
