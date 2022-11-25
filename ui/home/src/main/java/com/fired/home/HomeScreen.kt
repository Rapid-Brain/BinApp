package com.fired.home

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.fired.core.component.ErrorView
import com.fired.core.component.Loading
import com.fired.core.component.MyLazyColumn
import com.fired.detail.nav.navigateToDetail
import com.fired.rate.interactor.ExchangeRate

/**
 * @author yaya (@yahyalmh)
 * @since 05th November 2022
 */

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Loading(isLoading = state.isLoading)

    ErrorView(state, viewModel)

    MyLazyColumn(
        items = state.rates,
        cell = { rate -> RateCell(rate) { navController.navigateToDetail(rate.id) } }
    )
}

@Composable
private fun ErrorView(state: HomeUiState, viewModel: HomeViewModel) {
    ErrorView(
        isError = state.isError,
        errorMessage = state.errorMessage
    ) { viewModel.onEvent(HomeUiEvent.Retry) }
}

@Composable
private fun RateCell(rate: ExchangeRate, onClick: () -> Unit) {
    Card(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier
            .clickable { onClick.invoke() }
            .padding(vertical = 4.dp, horizontal = 8.dp)
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
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(68.dp)
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
    RateCell(rate = rate) {}
}
