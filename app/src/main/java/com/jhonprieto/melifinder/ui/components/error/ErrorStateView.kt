package com.jhonprieto.melifinder.ui.components.error

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jhonprieto.melifinder.R

@Composable
fun errorStateView(
    title: String = "Lo sentimos",
    message: String,
    iconRes: Int = R.drawable.img_error_man, // Pon aquí tu recurso, o elimina si usas emoji
    onRetry: (() -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Imagen central (puede ser emoji o un asset)
        // Si no tienes imagen puedes usar: Text(text = "😕", fontSize = 64.sp)
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier.size(96.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                color = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 18.sp,
                color = Color.Black.copy(alpha = 0.7f)
            ),
            modifier = Modifier.padding(horizontal = 16.dp),
            lineHeight = 26.sp
        )

        Spacer(modifier = Modifier.height(48.dp))

        onRetry?.let {
            OutlinedButton(
                onClick = it,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(32.dp))
                    .height(54.dp)
            ) {
                Text(
                    text = "Volver a intentar",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
        }
    }
}
