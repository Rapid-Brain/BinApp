package com.fired.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.common.RateCell
import com.fired.core.base.ReferenceDevices
import com.fired.core.component.*
import com.fired.core.component.bar.AppBar
import com.fired.detail.nav.navigateToDetail
import com.fired.rate.interactor.ExchangeRate
import com.fired.search.nav.navigateToSearch
import com.fired.ui.home.R

typealias coreStrings = com.fired.core.R.string

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
                navigationIconContentDescription = stringResource(id = coreStrings.menu),
                actionIcon = Icons.Default.Search,
                actionIconContentDescription = stringResource(id = R.string.searchIcon),
                onActionClick = { navController.navigateToSearch() }
            )
        }
    ) { padding ->
        val state = viewModel.state.value

        LoadingView(isLoading = state.isLoading)

        RetryView(
            isVisible = state.isRetry,
            errorMessage = state.retryMsg,
            icon = Icons.Default.Close
        ) {
            viewModel.onEvent(HomeUiEvent.Retry)
        }

        AutoRetryView(
            isVisible = state.isAutoRetry,
            errorMessage = state.retryMsg,
            icon = com.fired.core.component.icon.Icons.Face,
            hint = stringResource(id = coreStrings.autoRetryHint)
        )

        ContentView(
            isVisible = state is HomeUiState.Loaded,
            modifier = modifier.padding(padding),
            rates = state.rates
        ) { rateId ->
            navController.navigateToDetail(rateId)
        }
    }
}

@Composable
private fun ContentView(
    modifier: Modifier,
    isVisible: Boolean,
    rates: List<ExchangeRate>,
    navigateToDetail: (id: String) -> Unit
) {
    BaseLazyColumn(isVisible = isVisible, items = rates, modifier = modifier) { rate ->
        RateCell(
            rate = rate.rateUsd.toString(),
            symbol = rate.symbol,
            currencySymbol = rate.currencySymbol ?: rate.symbol,
            type = rate.type,
            onClick = { navigateToDetail(rate.id) }
        )
    }
}

@Composable
@ReferenceDevices
fun ContentPreview() = ContentView(modifier = Modifier, rates = ratesStub(), isVisible = true) {}

@Composable
private fun ratesStub(count: Int = 20): MutableList<ExchangeRate> {
    val rates = mutableListOf<ExchangeRate>()
    repeat(count) { rates.add(rateStub()) }
    return rates
}

@Composable
internal fun rateStub(): ExchangeRate = ExchangeRate(
    id = "1",
    symbol = "$",
    currencySymbol = "USD",
    type = "fiat",
    rateUsd = 0.165451654889.toBigDecimal()
)