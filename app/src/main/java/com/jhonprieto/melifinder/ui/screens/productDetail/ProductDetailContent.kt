@file:Suppress("MagicNumber")

package com.jhonprieto.melifinder.ui.screens.productDetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jhonprieto.domain.model.ProductDetail
import com.jhonprieto.melifinder.R
import com.jhonprieto.melifinder.ui.theme.Gray900
import com.jhonprieto.melifinder.ui.theme.Yellow400

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun productDetailContent(
    detail: ProductDetail,
    onBack: () -> Unit
) {
    Column {
        TopAppBar(
            title = {
                Text(
                    stringResource(R.string.product_detail_title),
                    fontWeight = FontWeight.Bold,
                    color = Gray900
                )
            },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Atrás", tint = Gray900)
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Yellow400,
                titleContentColor = Gray900,
                navigationIconContentColor = Gray900
            )
        )
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            productImagesCarousel(detail.pictures)
            productInfoSection(
                name = detail.name,
                price = getSimulatedPrice(),
                oldPrice = getSimulatedOldPrice(),
                rating = getSimulatedRating(),
                reviews = getSimulatedReviews()
            )
            HorizontalDivider(
                modifier = Modifier
                    .padding(vertical = 10.dp, horizontal = 20.dp)
                    .clip(RoundedCornerShape(50)),
                thickness = 3.dp,
                color = Color.LightGray.copy(alpha = 0.5f)
            )
            mainFeaturesSection(detail.mainFeatures)
            shortDescriptionSection(detail.shortDescription)
            productSpecsSection(detail.attributes)
        }
    }
}
