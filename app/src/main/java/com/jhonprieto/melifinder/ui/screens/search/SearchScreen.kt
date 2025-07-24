package com.jhonprieto.melifinder.ui.screens.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jhonprieto.melifinder.R
import com.jhonprieto.melifinder.ui.components.loadingView
import com.jhonprieto.melifinder.ui.theme.Gray900
import com.jhonprieto.melifinder.ui.theme.Yellow400
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun searchScreen(
    query: String,
    onProductClick: (String) -> Unit
) {
    val viewModel: SearchViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsState()

    // Solo dispara la búsqueda si el query no está vacío y cambia.
    LaunchedEffect(query) {
        if (query.isNotBlank()) viewModel.search(query)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.results),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = Gray900
                    )
                )
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Yellow400
            )
        )
        // Padding para separar del borde, igual que antes
        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            when (val state = uiState) {
                is SearchUiState.Idle -> searchIdleView()
                is SearchUiState.Loading -> loadingView()
                is SearchUiState.Success -> searchSuccessView(state.products, onProductClick)
                is SearchUiState.Error -> searchErrorView(
                    errorType = state.type,
                    onRetry = { viewModel.search(query) }
                )
                is SearchUiState.Empty -> searchEmptyView()
            }
        }
    }
}
