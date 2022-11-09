package com.fired.home

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
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
    ErrorView(errorMessage = state.value.errorMessage)

    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(state.value.rates) { rate ->
            RateCell(rate)
        }
    }

}

@Composable
private fun RateCell(rate: com.fired.rate.interactor.ExchangeRate) {
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
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp)
            ) {
                rate.currencySymbol?.let{
                    Text(
                        text = it,
                        style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.ExtraBold)
                    )
                }
                Text(text = rate.symbol)
                Text(text = rate.type)
                Text(
                    text = rate.rateUsd.toPlainString(),
                    style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.ExtraBold)
                )
            }
        }
    }
}

@Composable
fun ErrorView(errorMessage: String) {
    Text(text = errorMessage)
}

@Composable
fun Loading(isLoading: Boolean) {
    if (isLoading) {
        val animatedProgress = animateFloatAsState(
            targetValue = 1f,
            animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
        ).value

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    }
}
