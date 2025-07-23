package com.jhonprieto.melifinder.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jhonprieto.melifinder.R
import com.jhonprieto.melifinder.ui.theme.Yellow400

@Preview(showBackground = true, name = "HomeScreen")
@Composable
fun homeScreenPreview() {
    homeScreen(userName = stringResource(R.string.app_name))
}

@Composable
fun homeScreen(
    userName: String = stringResource(R.string.app_name),
    onSearchClick: () -> Unit = {}
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Yellow400,
        shadowElevation = 0.dp,
        shape = RoundedCornerShape(bottomStart = 28.dp, bottomEnd = 28.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(top = 40.dp, bottom = 24.dp),
            horizontalAlignment = Alignment.Start
        ) {
            homeHeader(userName)
            Spacer(modifier = Modifier.height(28.dp))
            trackSection(onSearchClick)
        }
    }
}

@Composable
fun homeHeader(userName: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Text(
            modifier = Modifier.padding(top = 14.dp),
            text = "$userName",
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        )
        notificationBellWithBadge(badgeCount = 2)
    }
}

@Composable
fun trackSection(onSearchClick: () -> Unit) {
    Text(
        text = stringResource(R.string.track_title),
        style = MaterialTheme.typography.titleMedium.copy(
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 20.sp
        )
    )
    Text(
        text = stringResource(R.string.track_hint),
        style = MaterialTheme.typography.bodyMedium.copy(
            color = Color.Black.copy(alpha = 0.7f)
        ),
        modifier = Modifier.padding(top = 6.dp, bottom = 14.dp)
    )
    searchInputRow(onClick = onSearchClick)
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun searchInputRow(onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .height(56.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White)
                .clickable { onClick() },
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text(
                    text = stringResource(R.string.track_placeholder),
                    style = MaterialTheme.typography.labelMedium.copy(color = Color.Gray)
                )
            }
        }
        Spacer(modifier = Modifier.width(12.dp))
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White)
                .size(56.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(R.string.scan_qr),
                tint = Color.Black,
                modifier = Modifier.size(28.dp)
            )
        }
    }
}

@Composable
fun notificationBellWithBadge(badgeCount: Int) {
    Box(
        contentAlignment = Alignment.TopEnd
    ) {
        Icon(
            imageVector = Icons.Default.Notifications,
            contentDescription = "Notificaciones",
            tint = Color.Black,
            modifier = Modifier.size(32.dp)
        )
        if (badgeCount > 0) {
            Box(
                modifier = Modifier
                    .offset(x = 8.dp, y = (-4).dp)
                    .size(18.dp)
                    .background(Color.Red, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = badgeCount.toString(),
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
