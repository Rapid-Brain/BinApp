package com.fired.core.component

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

/**
 * @author yaya (@yahyalmh)
 * @since 10th November 2022
 */

@Composable
fun LoadingView(isLoading: Boolean) {
    if (isLoading) {
        BaseCenterColumn { CircularProgressIndicator() }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingPreview() {
    LoadingView(isLoading = true)
}
