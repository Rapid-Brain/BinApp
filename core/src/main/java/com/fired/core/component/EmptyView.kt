package com.fired.core.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fired.core.R
import com.fired.core.component.icon.Icons

@Composable
fun EmptyView(
    modifier: Modifier = Modifier,
    isVisible: Boolean = true,
    icon: ImageVector,
    message: String,
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Column(
            modifier = modifier
                .background(MaterialTheme.colorScheme.secondary)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                modifier = Modifier
                    .padding(12.dp)
                    .size(80.dp),
                imageVector = icon,
                contentDescription = stringResource(id = R.string.emptyIcon),
                tint = MaterialTheme.colorScheme.onSurface
            )

            Text(
                modifier = Modifier.padding(top = 10.dp),
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                text = message
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun Preview() {
    EmptyView(isVisible = true, icon = Icons.ThumbUp, message = "Not Found")
}