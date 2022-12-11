package com.fired.search

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fired.core.component.BaseLazyColumn
import com.fired.core.component.Loading
import com.fired.core.component.SearchBar
import com.fired.detail.nav.navigateToDetail

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Loading(isLoading = state.isLoading)

    RetryErrorView(
        isError = state.isRetry,
        errorMessage = state.retryMsg
    )

    ErrorView(errorMessage = state.errorMsg, isError = state.isError) {
        viewModel.onEvent(SearchUiEvent.Retry)
    }

    EmptyView(modifier = modifier, isEmpty = state.isEmpty)


    ContentView(
        state = state,
        onQueryChange = { query -> viewModel.onEvent(SearchUiEvent.QueryChange(query)) },
        navigateToDetail = { id -> navController.navigateToDetail(id) }
    ) { navController.popBackStack() }

}


@Composable
private fun ContentView(
    state: SearchUiState,
    onQueryChange: (query: String) -> Unit,
    navigateToDetail: (id: String) -> Unit,
    onBackListener: () -> Unit
) {
    Column {

        SearchBar(onQueryChange = onQueryChange, onCancelClick = onBackListener)

        if (state is SearchUiState.Loaded) {
            BaseLazyColumn(items = state.result) { rate ->
                RateCell(
                    rate = rate.rateUsd.toString(),
                    symbol = rate.symbol,
                    currencySymbol = rate.currencySymbol ?: rate.symbol,
                    type = rate.type,
                    onClick = { navigateToDetail(rate.id) }
                )
            }
        }
    }
}
