package com.jhonprieto.melifinder.ui.screens.search

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.jhonprieto.melifinder.R

@Composable
fun searchEmptyView() {
    Text(stringResource(R.string.items_not_found), style = MaterialTheme.typography.bodyMedium)
}
