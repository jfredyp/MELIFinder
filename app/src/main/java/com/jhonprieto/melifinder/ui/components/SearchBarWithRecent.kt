@file:Suppress("MagicNumber")

package com.jhonprieto.melifinder.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jhonprieto.melifinder.R
import com.jhonprieto.melifinder.ui.theme.Yellow400
import kotlinx.coroutines.delay

private val SEARCH_BAR_HEIGHT = 50.dp
private val SEARCH_BAR_RADIUS = 22.dp
private val ICON_SIZE = 24.dp
private val RECENT_ICON_TINT = Color(0xFFBBBBBB)
private val RECENT_TEXT_COLOR = Color(0xFF222222)
private val RECENT_PADDING_TOP = 120.dp
private val RECENT_ITEM_HORIZONTAL = 20.dp
private val RECENT_ITEM_VERTICAL = 12.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun searchBarWithRecent(
    recentSearches: List<String> = listOf(
        "pixel 9 pro xl", "mi band 9", "thunderbox", "purificador de agua",
        "amortiguadores mazda 3", "ford fiesta st", "cascade", "hud", "hud mazda 3"
    ),
    onCancel: () -> Unit = {},
    onSearch: (String) -> Unit = {}
) {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    var isFocused by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        delay(150) // Pequeño delay para evitar glitches
        focusRequester.requestFocus()
    }

    Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
        searchBarTop(
            searchQuery = searchQuery,
            onQueryChange = { searchQuery = it },
            isFocused = isFocused,
            onFocusChange = { isFocused = it },
            onCancel = {
                focusManager.clearFocus()
                keyboardController?.hide()
                isFocused = false
                onCancel()
            },
            focusRequester = focusRequester,
            onSearch = onSearch
        )
        if (isFocused) {
            recentSearchesList(
                recentSearches = recentSearches,
                onSearch = { item ->
                    searchQuery = TextFieldValue(item)
                    onSearch(item)
                }
            )
        }
    }
}

@Suppress("LongParameterList", "LongMethod")
@Composable
private fun searchBarTop(
    searchQuery: TextFieldValue,
    onQueryChange: (TextFieldValue) -> Unit,
    isFocused: Boolean,
    onFocusChange: (Boolean) -> Unit,
    onCancel: () -> Unit,
    focusRequester: FocusRequester,
    onSearch: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .background(Yellow400)
            .fillMaxWidth()
            .padding(top = 55.dp, bottom = 18.dp, start = 20.dp, end = 20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = searchQuery,
                onValueChange = onQueryChange,
                placeholder = { Text(stringResource(R.string.track_placeholder)) },
                modifier = Modifier
                    .weight(1f)
                    .height(SEARCH_BAR_HEIGHT)
                    .background(Color.White, RoundedCornerShape(SEARCH_BAR_RADIUS))
                    .focusRequester(focusRequester)
                    .onFocusChanged { onFocusChange(it.isFocused) },
                singleLine = true,
                shape = RoundedCornerShape(SEARCH_BAR_RADIUS),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.Black,
                    focusedTextColor = Color.Black
                ),
                maxLines = 1,
                textStyle = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        if (searchQuery.text.isNotBlank()) {
                            onQueryChange(TextFieldValue(searchQuery.text))
                            onSearch(searchQuery.text)
                        }
                    }
                )
            )
            Spacer(modifier = Modifier.width(12.dp))
            if (isFocused) {
                Text(
                    text = "Cancelar",
                    color = Color.Black,
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier
                        .clickable { onCancel() }
                        .padding(start = 8.dp)
                )
            }
        }
    }
}

@Composable
private fun recentSearchesList(
    recentSearches: List<String>,
    onSearch: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = RECENT_PADDING_TOP)
    ) {
        recentSearches.forEach { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSearch(item) }
                    .padding(
                        horizontal = RECENT_ITEM_HORIZONTAL,
                        vertical = RECENT_ITEM_VERTICAL
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Reciente",
                    tint = RECENT_ICON_TINT,
                    modifier = Modifier.size(ICON_SIZE)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = item,
                    color = RECENT_TEXT_COLOR,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}
