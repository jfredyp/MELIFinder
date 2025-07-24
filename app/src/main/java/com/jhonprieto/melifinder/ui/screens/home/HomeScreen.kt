package com.jhonprieto.melifinder.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jhonprieto.domain.model.Category
import com.jhonprieto.melifinder.R
import com.jhonprieto.melifinder.ui.screens.category.categoriesSection
import com.jhonprieto.melifinder.ui.theme.Yellow400

@Preview(showBackground = true, name = "HomeScreen")
@Composable
fun homeScreenPreview() {
    homeScreen(userName = stringResource(R.string.app_name))
}

@Composable
fun homeScreen(
    userName: String = stringResource(R.string.app_name),
    onSearchClick: () -> Unit = {},
    onCategoryClick: (Category) -> Unit = {}
) {
    Column {
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
        Column { categoriesSection(onCategoryClick = onCategoryClick) }
    }
}
