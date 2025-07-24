package com.jhonprieto.melifinder.ui.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jhonprieto.domain.model.Product
import com.jhonprieto.melifinder.R
import com.jhonprieto.melifinder.ui.components.product.productListItem

@Composable
fun searchSuccessView(products: List<Product>, onProductClick: (String) -> Unit) {
    if (products.isEmpty()) {
        Text(stringResource(R.string.no_results), style = MaterialTheme.typography.bodyMedium)
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
                        onProductClick(products[idx].id ?: "")
                    }
                )
            }
        }
    }
}
