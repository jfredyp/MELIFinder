package com.jhonprieto.melifinder.ui.screens.search

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.jhonprieto.melifinder.R

@Composable
fun searchIdleView() {
    Text(
        text = stringResource(R.string.write_something),
        style = MaterialTheme.typography.bodyMedium
    )
}
