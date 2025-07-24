@file:Suppress("MagicNumber")

package com.jhonprieto.melifinder.ui.screens.productDetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jhonprieto.melifinder.R

private val imageCarouselHeight = 280.dp
private val productCardCorner = 18.dp
private val ratingStarSize = 18.dp

@Composable
fun productImagesCarousel(images: List<String>) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(imageCarouselHeight)
            .padding(vertical = 12.dp)
    ) {
        items(images) { url ->
            AsyncImage(
                model = url,
                contentDescription = stringResource(R.string.product_image),
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .clip(RoundedCornerShape(productCardCorner))
                    .fillMaxHeight()
                    .aspectRatio(1f)
            )
        }
    }
}

@Composable
fun productInfoSection(
    name: String,
    price: Double,
    oldPrice: Double?,
    rating: Float,
    reviews: Int
) {
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        Text(name, style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold), maxLines = 2)
        Spacer(Modifier.height(4.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "$${"%,.0f".format(price)}",
                style = MaterialTheme.typography.titleMedium.copy(color = Color.Red, fontWeight = FontWeight.Bold)
            )
            if (oldPrice != null) {
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "$${"%,.0f".format(oldPrice)}",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.Gray,
                        textDecoration = TextDecoration.LineThrough
                    )
                )
                Spacer(Modifier.width(8.dp))
                Text("SAVE 10%", color = Color.Red, style = MaterialTheme.typography.labelSmall)
            }
        }
        Spacer(Modifier.height(4.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            ratingBar(rating)
            Spacer(Modifier.width(4.dp))
            Text("($reviews)", color = Color.Gray, style = MaterialTheme.typography.bodySmall)
            Spacer(Modifier.width(8.dp))
            Icon(
                Icons.Default.FavoriteBorder,
                contentDescription = stringResource(R.string.Wishlist),
                tint = Color.Gray
            )
            Spacer(Modifier.width(8.dp))
            Icon(
                Icons.Default.Share,
                contentDescription = stringResource(R.string.content_description),
                tint = Color.Gray
            )
        }
    }
}

@Composable
fun mainFeaturesSection(features: List<String>) {
    if (features.isNotEmpty()) {
        Column(Modifier.padding(horizontal = 20.dp, vertical = 8.dp)) {
            features.forEach { Text("• $it", style = MaterialTheme.typography.bodyMedium) }
        }
    }
}

@Composable
fun shortDescriptionSection(shortDescription: String?) {
    shortDescription?.let {
        Text(
            it,
            style = MaterialTheme.typography.bodySmall,
            color = Color.DarkGray,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)
        )
    }
}

@Composable
fun productSpecsSection(attributes: Map<String, String>) {
    if (attributes.isNotEmpty()) {
        Column(Modifier.padding(horizontal = 20.dp, vertical = 8.dp)) {
            Text(
                "Especificaciones",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(6.dp))
            attributes.forEach { (k, v) ->
                Text("$k: $v", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Composable
fun addToCartSection() {
    Button(
        onClick = { /* Acción de agregar al carrito */ },
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text("Add to cart", style = MaterialTheme.typography.titleMedium)
    }
}

@Composable
fun ratingBar(rating: Float, stars: Int = 5) {
    Row {
        repeat(stars) { idx ->
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = if (idx < rating.toInt()) Color(0xFFFFD700) else Color.Gray,
                modifier = Modifier.size(ratingStarSize)
            )
        }
    }
}
