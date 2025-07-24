@file:OptIn(ExperimentalMaterial3Api::class)

package com.jhonprieto.melifinder.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.jhonprieto.melifinder.ui.theme.Gray900
import com.jhonprieto.melifinder.ui.theme.Yellow400

@Composable
fun appTopBar(
    title: String,
    backgroundColor: Color = Yellow400,
    contentColor: Color = Gray900
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                color = contentColor
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = backgroundColor
        )
    )
}
