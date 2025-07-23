package com.jhonprieto.melifinder.ui.components.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jhonprieto.domain.model.Product

@Composable
fun productItem(product: Product) {
    Card(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .background(Color.White)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = product.title ?: "Sin título")
            // Puedes agregar más info, precio, imagen, etc.
        }
    }
}
