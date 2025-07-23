package com.jhonprieto.melifinder.ui.screens.productDetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun detailLoadingView() = Box(
    Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center
) { CircularProgressIndicator() }

@Composable
fun detailErrorView(message: String) = Box(
    Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center
) { Text("Error: $message", color = Color.Red) }
