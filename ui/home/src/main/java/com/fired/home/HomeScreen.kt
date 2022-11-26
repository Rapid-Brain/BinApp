package com.fired.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.fired.core.component.BaseLazyColumn
import com.fired.core.component.ErrorView
import com.fired.core.component.Loading
import com.fired.detail.nav.navigateToDetail
import com.fired.home.components.RateCell
import com.fired.home.components.rateStub
import com.fired.rate.interactor.ExchangeRate

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Loading(isLoading = state.isLoading)

    ErrorView(state, viewModel)

    BaseLazyColumn(
        items = state.rates,
        cell = {
            RateCell(
                rate = it.rateUsd.toString(),
                symbol = it.symbol,
                currencySymbol = it.currencySymbol ?: it.symbol,
                type = it.type,
                onClick = { navController.navigateToDetail(it.id) })
        }
    )
}

@Composable
private fun ErrorView(state: HomeUiState, viewModel: HomeViewModel) {
    ErrorView(
        isError = state.isError,
        errorMessage = state.errorMessage
    ) { viewModel.onEvent(HomeUiEvent.Retry) }
}

@Preview
@Composable
fun RateCellPreview() {
    val rate = ratesStub()
    BaseLazyColumn(
        items = rate,
        cell = {
            RateCell(
                rate = it.rateUsd.toString(),
                symbol = it.symbol,
                currencySymbol = it.currencySymbol ?: it.symbol,
                type = it.type,
                onClick = { })
        }
    )
}

@Composable
private fun ratesStub(): MutableList<ExchangeRate> {
    val rates = mutableListOf<ExchangeRate>()
    val count = 20
    repeat(count) {
        rates.add(rateStub())
    }
    return rates
}