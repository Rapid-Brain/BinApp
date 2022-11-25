package com.fired.core.component

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

/**
 * @author yaya (@yahyalmh)
 * @since 10th November 2022
 */

@Composable
fun Loading(isLoading: Boolean) {
    if (isLoading) {
        CenterColumn { CircularProgressIndicator() }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingPreview() {
    Loading(isLoading = true)
}
