package com.fired.core.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
    isVisible: Boolean = true,
    cell: @Composable (T) -> Unit
) {
    AnimatedVisibility(visible = isVisible, enter = fadeIn(), exit = fadeOut()) {
        LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
            items(items) { cell(it) }
        }
    }
}