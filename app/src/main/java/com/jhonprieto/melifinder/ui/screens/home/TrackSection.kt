package com.jhonprieto.melifinder.ui.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jhonprieto.melifinder.R

@Composable
fun trackSection(onSearchClick: () -> Unit) {
    Text(
        text = stringResource(R.string.track_title),
        style = MaterialTheme.typography.titleMedium.copy(
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 20.sp
        )
    )
    Text(
        text = stringResource(R.string.track_hint),
        style = MaterialTheme.typography.bodyMedium.copy(
            color = Color.Black.copy(alpha = 0.7f)
        ),
        modifier = Modifier.padding(top = 6.dp, bottom = 14.dp)
    )
    searchInputRow(onClick = onSearchClick)
}
