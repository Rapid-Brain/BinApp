package com.fired.core.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun <T> BaseLazyColumn(
    modifier: Modifier = Modifier,
    items: List<T>,
    cell: @Composable (T) -> Unit
) {
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items) { cell(it) }
    }
}