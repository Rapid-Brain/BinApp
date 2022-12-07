package com.fired.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.fired.core.base.ReferenceDevices
import com.fired.core.component.AppBar
import com.fired.core.component.BaseLazyColumn
import com.fired.core.component.ErrorView
import com.fired.core.component.Loading
import com.fired.detail.nav.navigateToDetail
import com.fired.home.components.RateCell
import com.fired.home.components.rateStub
import com.fired.rate.interactor.ExchangeRate
import com.fired.search.nav.navigateToSearch
import com.fired.ui.home.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    Scaffold(
        contentColor = MaterialTheme.colors.onBackground,
        topBar = {
            AppBar(
                titleRes = R.string.home,
                modifier = Modifier.zIndex(-1F),
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent
                ),
                navigationIcon = Icons.Default.Menu,
                navigationIconContentDescription = "Menu",
                actionIcon = Icons.Default.Search,
                actionIconContentDescription = "Search",
                onActionClick = { navController.navigateToSearch() }
            )
        }
    ) { padding ->
        val state = viewModel.state.value
        Loading(isLoading = state.isLoading)

        ErrorView(state, viewModel)

        BaseLazyColumn(items = state.rates, modifier = modifier.padding(padding)) { rate ->
            RateCell(
                rate = rate.rateUsd.toString(),
                symbol = rate.symbol,
                currencySymbol = rate.currencySymbol ?: rate.symbol,
                type = rate.type,
                onClick = {
                    navController.navigateToDetail(rate.id)
                }
            )
        }
    }
}

@Composable
private fun ErrorView(state: HomeUiState, viewModel: HomeViewModel) {
    ErrorView(
        isError = state.isError,
        errorMessage = state.errorMessage
    ) { viewModel.onEvent(HomeUiEvent.Retry) }
}

@Composable
@ReferenceDevices
fun ContentPreview() {
    val rates = ratesStub()
    BaseLazyColumn(items = rates, modifier = Modifier) { rate ->
        RateCell(
            rate = rate.rateUsd.toString(),
            symbol = rate.symbol,
            currencySymbol = rate.currencySymbol ?: rate.symbol,
            type = rate.type,
            onClick = {}
        )
    }
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