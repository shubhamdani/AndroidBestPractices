package com.example.feature_dashboard.composeview

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.feature_dashboard.R
import com.example.feature_dashboard.domain.CurrentWeather

@Composable
fun DashboardComposeView(
    currentWeather: CurrentWeather,
) {
    Box {
        Image(
            painter = painterResource(id = R.drawable.sunny_wallpaper),
            "background image",
            Modifier
                .fillMaxHeight(1f)
                .fillMaxWidth(1f),
            contentScale = ContentScale.FillBounds
        )
        Column(
            Modifier.padding(16.dp)
        ) {
            HeaderTextView(currentWeather.location)
            BodyTextView(stringResource(id = R.string.temperature).format(currentWeather.temp))
            BodyTextView(stringResource(id = R.string.min_temperature).format(currentWeather.minTemp))
            BodyTextView(stringResource(id = R.string.max_temperature).format(currentWeather.maxTemp))
            BodyTextView(stringResource(id = R.string.humidity).format(currentWeather.humidity))
        }
    }
}

@Composable
private fun HeaderTextView(headerText: String) {
    Text(
        text = headerText,
        fontSize = 60.sp,
        color = Color.White,
        fontFamily = FontFamily.Monospace
    )
}

@Composable
private fun BodyTextView(weatherInfo: String) {
    Spacer(modifier = Modifier.padding(8.dp))
    Text(
        text = weatherInfo, fontSize = 30.sp, color = Color.White, fontFamily = FontFamily.Monospace
    )
}
