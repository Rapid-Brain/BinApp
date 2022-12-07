package com.fired.search

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fired.core.component.BaseLazyColumn
import com.fired.core.component.Loading
import com.fired.detail.nav.navigateToDetail
import kotlinx.coroutines.flow.MutableStateFlow

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

    EmptyView(isEmpty = state.isEmpty)
//    ContentView(state, viewModel.searchFlow){navController.popBackStack()}
    Column {
        SearchBar(viewModel) { navController.popBackStack() }
        if (state is SearchUiState.Loaded) {
            BaseLazyColumn(items = state.result, modifier = Modifier.padding(5.dp)) { rate ->
                RateCell(
                    rate = rate.rateUsd.toString(),
                    symbol = rate.symbol,
                    currencySymbol = rate.currencySymbol ?: rate.symbol,
                    type = rate.type,
                    onClick = { navController.navigateToDetail(rate.id) }
                )
            }
        }
    }
}

@Composable
private fun ContentView(
    state: SearchUiState,
    searchFlow: MutableStateFlow<String>,
    onBackListener: () -> Unit
) {

}

@Composable
private fun SearchBar(viewModel: SearchViewModel, onCancelClick: () -> Unit) {
    val query = viewModel.state.value.query
    val requester = FocusRequester()

    Row(
        modifier = Modifier.background(Color.Gray),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp, top = 10.dp, end = 2.dp, bottom = 4.dp)
                .background(
                    MaterialTheme.colorScheme.surface,
                    RoundedCornerShape(20)
                )
                .padding(6.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Icon(
                modifier = Modifier.size(20.dp),
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(id = R.string.search),
                tint = MaterialTheme.colorScheme.onSurface
            )
            BasicTextField(modifier = Modifier
                .focusRequester(requester)
                .weight(1f)
                .background(
                    MaterialTheme.colorScheme.surface,
                    RoundedCornerShape(20)
                ),
                singleLine = true,
                value = query,
                onValueChange = { viewModel.onEvent(SearchUiEvent.QueryChange(it)) },
                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(2.dp)
                    ) {
                        AnimatedVisibility(
                            visible = query.isEmpty(),
                            enter = fadeIn(initialAlpha = 0.3f),
                            exit = fadeOut()
                        ) {
                            Text(
                                text = "Search Currencies",
                                color = if (isSystemInDarkTheme()) Color(0xFF969EBD) else Color.Gray,
                                fontSize = 14.sp
                            )
                        }
                    }
                    innerTextField()
                }
            )
            AnimatedVisibility(
                visible = query.isNotEmpty(),
                enter = fadeIn(initialAlpha = 0.3f),
                exit = fadeOut()
            ) {
                Icon(
                    modifier = Modifier
                        .clickable { viewModel.onEvent(SearchUiEvent.ClearSearch) }
                        .size(20.dp),
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(id = R.string.search),
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
        TextButton(
            modifier = Modifier
                .wrapContentSize()
                .padding(end = 4.dp),
            contentPadding = PaddingValues(4.dp),
            onClick = onCancelClick
        ) {
            Text(color = MaterialTheme.colorScheme.onSurface, fontSize = 16.sp, text = "Cancel")
        }
    }
    SideEffect { requester.requestFocus() }

}

@Preview
@Composable
fun ContentPreview() {
//    ContentView(){}
}