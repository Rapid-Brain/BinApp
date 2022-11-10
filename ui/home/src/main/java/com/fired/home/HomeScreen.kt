package com.fired.home

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fired.rate.interactor.ExchangeRate

/**
 * @author yaya (@yahyalmh)
 * @since 05th November 2022
 */

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state
    Loading(isLoading = state.value.isLoading)
    ErrorView(
        isError = state.value.isError,
        errorMessage = state.value.errorMessage
    ) { viewModel.onEvent(HomeUiEvent.Retry) }

    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(state.value.rates) { rate ->
            RateCell(rate)
        }
    }

}

@Composable
private fun RateCell(rate: ExchangeRate) {
    Card(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
        ) {
            Row(
                modifier = Modifier
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(68.dp)
                        .shadow(10.dp)
                        .clip(CircleShape)
                        .background(Color.Red),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        textAlign = TextAlign.Center,
                        text = rate.currencySymbol ?: rate.symbol,
                        style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.ExtraBold)
                    )
                }
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        text = rate.rateUsd.toPlainString(),
                        style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.ExtraBold)
                    )
                    Text(
                        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
                        text = rate.symbol,
                        style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.ExtraBold)
                    )
                    Text(
                        text = rate.type,
                        style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.ExtraBold)
                    )
                }
            }
        }
    }
}

@Composable
fun ErrorView(isError: Boolean, errorMessage: String, onRetry: () -> Unit) {
    if (isError) {
        Column(
            modifier = Modifier.fillMaxSize(),
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

@Composable
fun Loading(isLoading: Boolean) {
    if (isLoading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    }
}

@Preview
@Composable
fun ErrorViewPreview() {
    ErrorView(isError = true, errorMessage = "This is a error message") {}
}

@Preview
@Composable
fun RateCellPreview() {
    val rate = ExchangeRate(
        id = "1",
        symbol = "$",
        currencySymbol = "#",
        type = "fiat",
        rateUsd = 0.165451654889.toBigDecimal()
    )
    RateCell(rate = rate)
}
