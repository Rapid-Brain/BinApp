package com.fired.search

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.common.RateCell
import com.fired.core.component.*
import com.fired.core.component.bar.SearchBar
import com.fired.core.component.icon.Icons
import com.fired.detail.nav.navigateToDetail

typealias coreString = com.fired.core.R.string
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    LoadingView(isLoading = state.isLoading)

    AutoRetryView(
        isVisible = state.isAutoRetry,
        errorMessage = state.retryMsg,
        icon = Icons.Face,
        hint = stringResource(id = coreString.autoRetryHint)
    )

    RetryView(
        isVisible = state.isRetry,
        errorMessage = state.errorMsg,
        icon = Icons.Face
    ) { viewModel.onEvent(SearchUiEvent.Retry) }

    EmptyView(
        modifier = modifier, isVisible = state.isEmpty, icon = Icons.Face,
        message = stringResource(id = R.string.noItemFound)
    )

    Column {
        SearchBar(onQueryChange = { query -> viewModel.onEvent(SearchUiEvent.QueryChange(query)) },
            onCancelClick = { navController.popBackStack() })

        ContentView(
            state = state,
            navigateToDetail = { id -> navController.navigateToDetail(id) }
        )
    }

}

@Composable
private fun ContentView(
    state: SearchUiState,
    navigateToDetail: (id: String) -> Unit,
) {
    BaseLazyColumn(isVisible = state is SearchUiState.Loaded, items = state.result) { rate ->
        RateCell(
            rate = rate.rateUsd.toString(),
            symbol = rate.symbol,
            currencySymbol = rate.currencySymbol ?: rate.symbol,
            type = rate.type,
            onClick = { navigateToDetail(rate.id) }
        )
    }
}
