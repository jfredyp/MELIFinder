package com.jhonprieto.melifinder.ui.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jhonprieto.domain.error.ApiErrorType
import com.jhonprieto.domain.model.Product
import com.jhonprieto.melifinder.ui.components.error.errorStateView
import com.jhonprieto.melifinder.ui.components.product.productListItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun searchScreen(
    query: String
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
            .padding(16.dp)
    ) {
        when (val state = uiState) {
            is SearchUiState.Idle -> searchIdleView()
            is SearchUiState.Loading -> searchLoadingView()
            is SearchUiState.Success -> searchSuccessView(state.products)
            is SearchUiState.Error -> searchErrorView(
                errorType = state.type,
                onRetry = { viewModel.search(query) }
            )
            is SearchUiState.Empty -> searchEmptyView()
        }
    }
}

@Composable
private fun searchIdleView() {
    Text(
        text = "Escribe algo para buscar productos…",
        style = MaterialTheme.typography.bodyMedium
    )
}

@Composable
private fun searchLoadingView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun searchSuccessView(products: List<Product>) {
    if (products.isEmpty()) {
        Text("Sin resultados", style = MaterialTheme.typography.bodyMedium)
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(count = products.size) { idx ->
                productListItem(
                    product = products[idx],
                    onClick = {
                        println("onProductClick(products[idx])")
                    } // Aquí puedes manejar el click en el producto
                )
            }
        }
    }
}

@Composable
private fun searchEmptyView() {
    Text("No se encontraron resultados.", style = MaterialTheme.typography.bodyMedium)
}

@Composable
private fun searchErrorView(
    errorType: ApiErrorType,
    onRetry: () -> Unit
) {
    val (title, message) = when (errorType) {
        ApiErrorType.UNAUTHORIZED -> "No autorizado" to "Debes iniciar sesión para continuar."
        ApiErrorType.FORBIDDEN -> "Acceso denegado" to "No tienes permisos para realizar esta acción."
        ApiErrorType.NOT_FOUND -> "No encontrado" to "No se encontraron productos para tu búsqueda."
        ApiErrorType.SERVER_ERROR -> "Error del servidor" to "Inténtalo de nuevo más tarde."
        ApiErrorType.NETWORK -> "Sin conexión" to "Revisa tu conexión a internet."
        ApiErrorType.UNKNOWN -> "Error desconocido" to "Algo salió mal. Intenta nuevamente."
    }
    errorStateView(
        title = title,
        message = message,
        onRetry = onRetry
    )
}
