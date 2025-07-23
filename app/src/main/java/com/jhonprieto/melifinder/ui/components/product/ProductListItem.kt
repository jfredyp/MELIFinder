@file:Suppress("MagicNumber")

package com.jhonprieto.melifinder.ui.components.product

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jhonprieto.domain.model.Product

@Suppress("LongMethod")
@Composable
fun productListItem(
    product: Product,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 1.dp, vertical = 2.dp) // Espaciado entre tarjetas
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp), // Bordes más grandes y suaves
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .background(Color.White) // Fondo blanco como en el modelo
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = product.mainImageUrl,
                contentDescription = product.title ?: "Sin nombre",
                modifier = Modifier
                    .size(66.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFF4F4F4))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = product.title ?: "Sin nombre",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color(0xFF333333)
                    ),
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = product.price?.let { "$${"%,.0f".format(it) }" } ?: "$ ----",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    ),
                    color = Color(0xFF333333)
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = product.condition?.replaceFirstChar { it.uppercase() } ?: "Condición desconocida",
                    style = MaterialTheme.typography.labelSmall,
                    color = if (product.condition?.lowercase() == "nuevo" || product.condition?.lowercase() == "new") {
                        Color(0xFF8A8A8A)
                    } else {
                        Color(0xFF2ECC71)
                    }
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Filled.FavoriteBorder,
                contentDescription = "Favorito",
                tint = Color(0xFFCCCCCC),
                modifier = Modifier.size(22.dp)
            )
        }
    }
}
