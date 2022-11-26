package com.fired.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fired.core.base.ReferenceDevices
import com.fired.core.component.ErrorView
import com.fired.core.component.Loading
import com.fired.rate.interactor.ExchangeDetailRate

/**
 * @author yaya (@yahyalmh)
 * @since 10th November 2022
 */

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Loading(isLoading = state.isLoading)

    ErrorView(
        isError = state.isError,
        errorMessage = state.errorMsg
    ) { viewModel.onEvent(DetailUiEvent.Retry) }

    ContentView(modifier, state)
}

@Composable
private fun ContentView(
    modifier: Modifier,
    state: DetailUiState
) {
    if (state is DetailUiState.Loaded) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(150.dp)
                    .background(Color.Blue)
                    .padding(vertical = 15.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = state.rate?.let { it.currencySymbol ?: it.symbol } ?: "",
                    style = MaterialTheme.typography.h3.copy(fontWeight = FontWeight.ExtraBold))
            }
            state.rate?.run {
                Text(
                    modifier = modifier.padding(top = 10.dp),
                    textAlign = TextAlign.Center,
                    text = "Rate Usd: ${rateUsd.toPlainString()}",
                    style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.ExtraBold)
                )
                Text(
                    modifier = Modifier.padding(vertical = 20.dp),
                    text = "Symbol: $symbol",
                    style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.ExtraBold)
                )
                Text(
                    modifier = Modifier.padding(bottom = 10.dp),
                    text = "Type: $type",
                    style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.ExtraBold)
                )
                Text(
                    text = "TimeStamp: $timestamp",
                    style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.ExtraBold)
                )
            }
        }
    }
}

@Composable
@ReferenceDevices
fun ContentPreview() {
    val rateDetail = ExchangeDetailRate(
        "Id",
        symbol = "$",
        currencySymbol = "##",
        type = "Fiat",
        rateUsd = 0.16544654.toBigDecimal(),
        timestamp = 1324654312
    )
    val state = DetailUiState.Loaded(rateDetail)
    ContentView(modifier = Modifier, state = state)
}