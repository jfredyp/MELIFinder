package com.jhonprieto.search

@Composable
fun SearchScreen(viewModel: SearchViewModel, onItemClick: (Product) -> Unit) {
    val state by viewModel.state.collectAsState()
    var query by remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Buscar productos") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = { viewModel.search(query) }, modifier = Modifier.padding(top = 8.dp)) {
            Text("Buscar")
        }

        when (state) {
            is UiState.Loading -> CircularProgressIndicator()
            is UiState.Success -> ProductListScreen((state as UiState.Success<List<Product>>).data, onItemClick)
            is UiState.Error -> Text("Error: ${(state as UiState.Error).message}")
            UiState.Empty -> Text("Sin resultados")
        }
    }
}
