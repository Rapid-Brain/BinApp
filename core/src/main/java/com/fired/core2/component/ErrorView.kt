package com.fired.core2.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * @author yaya (@yahyalmh)
 * @since 10th November 2022
 */

@Composable
fun ErrorView(isError: Boolean, errorMessage: String, onRetry: () -> Unit) {
    if (isError) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(modifier = Modifier.padding(4.dp), text = errorMessage)
            Button(modifier = Modifier.padding(top = 10.dp), onClick = onRetry) {
                Text(modifier = Modifier.padding(4.dp), text = "Retry")
            }
        }
    }
}

@Preview
@Composable
fun ErrorViewPreview() {
    ErrorView(isError = true, errorMessage = "This is a error message") {}
}
