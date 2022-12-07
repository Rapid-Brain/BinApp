package com.fired.search

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fired.core.component.icon.Icons

@Composable
fun EmptyView(isEmpty: Boolean) {
    if (isEmpty.not()){return}
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Icon(
            modifier = Modifier
                .padding(12.dp)
                .size(80.dp),
            imageVector = Icons.ThumbUp,
            contentDescription = "not found",
            tint = MaterialTheme.colorScheme.onSurface
        )

        Text(
            modifier = Modifier.padding(top = 10.dp),
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 16.sp,
            fontStyle = FontStyle.Italic,
            text = "No Item Found"
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun Preview() {
    EmptyView(true)
}