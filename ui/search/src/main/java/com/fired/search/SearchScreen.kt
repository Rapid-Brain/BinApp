package com.fired.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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

        SearchBar(
            onQueryChange = onQueryChange,
            onCancelClick = onBackListener
        )

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

@Composable
private fun SearchBar(onQueryChange: (query: String) -> Unit, onCancelClick: () -> Unit) {
    val requester = FocusRequester()
    var value by remember {
        mutableStateOf("")
    }

    Row(
        modifier = Modifier.background(MaterialTheme.colorScheme.surface),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .padding(start = 4.dp)
                .background(
                    MaterialTheme.colorScheme.secondary,
                    RoundedCornerShape(20)
                )
                .padding(6.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Icon(
                modifier = Modifier.size(20.dp),
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(id = R.string.search),
//                tint = MaterialTheme.colorScheme.onSurface
            )
            BasicTextField(modifier = Modifier
                .focusRequester(requester)
                .weight(1f)
                .padding(start = 2.dp)
                .height(24.dp)
                .background(color = Color.Transparent, shape = RoundedCornerShape(20)),
                singleLine = true,
                value = value,
                onValueChange = {
                    value = it
                    onQueryChange(it)
                },
                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(2.dp),
                        verticalAlignment = Alignment.CenterVertically,

                        ) {
                        AnimatedVisibility(
                            visible = value.isEmpty(),
                            enter = fadeIn(initialAlpha = 0.3f),
                            exit = fadeOut()
                        ) {
                            Text(
                                text = "Search Currencies",
                                color = MaterialTheme.colorScheme.onSecondary,
                            )
                        }
                    }
                    innerTextField()
                }
            )
            AnimatedVisibility(
                visible = value.isNotEmpty(),
                enter = fadeIn(initialAlpha = 0.3f),
                exit = fadeOut()
            ) {
                Icon(
                    modifier = Modifier
                        .clickable {
                            value = ""
                            onQueryChange(value)
                        }
                        .size(20.dp),
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(id = R.string.search),
//                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
        TextButton(
            modifier = Modifier
                .wrapContentSize()
                .padding(start = 4.dp, end = 4.dp),
            contentPadding = PaddingValues(4.dp),
            onClick = onCancelClick
        ) {
            Text(color = MaterialTheme.colorScheme.onSurface, fontSize = 16.sp, text = "Cancel")
        }
    }
    Spacer(
        modifier = Modifier
            .height(1.dp)
            .background(MaterialTheme.colorScheme.onPrimary)
    )
    SideEffect { requester.requestFocus() }
}

@Preview
@Composable
fun SearchPreview() {
    SearchBar(onQueryChange = {}) {}
}

