package com.example.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RateCell(
    rate: String,
    symbol: String,
    currencySymbol: String,
    type: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier
        .clickable { onClick() }
        .padding(8.dp)) { Content(modifier, currencySymbol, rate, symbol, type) }
}

@Composable
private fun Content(
    modifier: Modifier,
    currencySymbol: String,
    rate: String,
    symbol: String,
    type: String
) {
    Row(
        modifier = modifier
            .padding(12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = modifier
                .size(68.dp)
                .clip(CircleShape)
                .background(Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = currencySymbol,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold
            )
        }
        Column(modifier = modifier.padding(12.dp)) {
            Text(
                text = rate,
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = modifier.height(10.dp))

            Text(
                text = symbol,
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = modifier.height(10.dp))

            Text(
                text = type,
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

@Preview
@Composable
fun RateCellPreview() {
    RateCell(
        rate = "3.343234342",
        symbol = "$$",
        currencySymbol = "USD",
        type = "Fiat",
        onClick = {}
    )
}
