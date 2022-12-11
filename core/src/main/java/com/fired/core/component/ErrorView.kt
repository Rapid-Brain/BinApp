package com.fired.core.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fired.core.R
import com.fired.core.component.icon.Icons

/**
 * @author yaya (@yahyalmh)
 * @since 10th November 2022
 */

@Composable
fun RetryView(
    isVisible: Boolean = true, errorMessage: String, icon: ImageVector, onRetry: () -> Unit
) {
    AnimatedVisibility(visible = isVisible, enter = fadeIn(), exit = fadeOut()) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier
                    .padding(12.dp)
                    .size(80.dp),
                imageVector = icon,
                contentDescription = "retry icon",
                tint = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(10.dp))


            Text(
                text = errorMessage,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(onClick = onRetry) {
                Text(text = stringResource(R.string.txt_retry))
            }
        }
    }
}


@Composable
fun AutoRetryView(
    isVisible: Boolean = true,
    errorMessage: String,
    icon: ImageVector,
    hint: String,
) {
    AnimatedVisibility(
        visible = isVisible, enter = fadeIn(), exit = fadeOut()
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier
                    .padding(12.dp)
                    .size(80.dp),
                imageVector = icon,
                contentDescription = "Auto retry icon",
                tint = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = errorMessage,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = hint,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RetryPreview() {
    RetryView(icon = Icons.Face, errorMessage = "This is a error message") {}
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AutoRetryPreview() {
    AutoRetryView(
        icon = Icons.Face, errorMessage = "This is a error message", hint = "Go online to try again"
    )
}
