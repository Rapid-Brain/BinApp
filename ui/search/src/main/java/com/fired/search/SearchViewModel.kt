package com.fired.search

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.fired.core.base.BaseViewModel
import com.fired.core.base.UIEvent
import com.fired.core.base.UIState
import com.fired.rate.interactor.ExchangeRate
import com.fired.rate.interactor.ExchangeRateInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import java.io.IOException
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val exchangeRateInteractor: ExchangeRateInteractor
) : BaseViewModel<SearchUiState, SearchUiEvent>(SearchUiState.Empty()) {
    private val searchFlow = MutableStateFlow(state.value.query)
    lateinit var job: Job

    init {
        searchFlow
            .debounce(300)
            //            .distinctUntilChanged()
            .onEach { query ->
                if (::job.isInitialized && job.isActive) {
                    job.cancel()
                }

                when {
                    query.isBlank() && query.isEmpty() -> setState(SearchUiState.Empty())
                    query.isNotResetState() -> searchQuery(query)
                }
            }.launchIn(viewModelScope)
        Log.d("TAGGGGGGG", "init: ")
    }

    private fun searchQuery(query: String) {
        job = exchangeRateInteractor
            .getRates()
            .retryWithPolicy(DefaultRetryPolicy()) { handleRetry() }
            .catch { e -> handleError(e) }
            .map { exchangeRates ->
                exchangeRates.filter {
                    it.symbol.contains(query, ignoreCase = true)
                }
            }
            .onEach { result ->
                when {
                    result.isEmpty() -> setState(SearchUiState.Empty(state.value.query))
                    else -> setState(SearchUiState.Loaded(result, state.value.query))
                }
            }.launchIn(viewModelScope)
    }

    private fun handleError(e: Throwable) {
//        searchFlow.value = restString
        val errorMessage = e.message ?: "Error while fetching the exchange rate"
        setState(SearchUiState.Error(errorMessage, query = state.value.query))
    }

    private fun handleRetry() {
        val retryMsg = "No result for \"${state.value.query}\""
        setState(SearchUiState.Retry(retryMsg, query = state.value.query))
    }

    override fun onEvent(event: SearchUiEvent) {
        when (event) {
            SearchUiEvent.Retry -> {
                searchFlow.value = state.value.query
                setState(SearchUiState.Search(state.value.query))
            }
            is SearchUiEvent.QueryChange -> {
                searchFlow.tryEmit(event.text)
                setState(SearchUiState.Search(event.text))
            }
            SearchUiEvent.ClearSearch -> {
                searchFlow.value = ""
                setState(SearchUiState.Empty())
            }
        }
    }
}

fun String.isNotResetState() = this != restString
const val restString = "___"

fun <T> Flow<T>.retryWithPolicy(
    retryPolicy: RetryPolicy,
    retryHandler: () -> Unit
): Flow<T> {
    var currentDelay = retryPolicy.delayMillis
    val delayFactor = retryPolicy.delayFactor
    return retryWhen { cause, attempt ->
        retryHandler()
        if (cause is IOException && attempt < retryPolicy.numRetries) {
            delay(currentDelay)
            currentDelay *= delayFactor
            return@retryWhen true
        } else {
            return@retryWhen false
        }
    }
}

interface RetryPolicy {
    val numRetries: Long
    val delayMillis: Long
    val delayFactor: Long
}

data class DefaultRetryPolicy(
    override val numRetries: Long = 6,
    override val delayMillis: Long = 600,
    override val delayFactor: Long = 1
) : RetryPolicy

sealed interface SearchUiEvent : UIEvent {
    object Retry : SearchUiEvent
    class QueryChange(val text: String) : SearchUiEvent
    object ClearSearch : SearchUiEvent
}

sealed class SearchUiState(
    val result: List<ExchangeRate> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isRetry: Boolean = false,
    val isEmpty: Boolean = false,
    val errorMsg: String = "",
    val retryMsg: String = "",
    var query: String
) : UIState {
    class Loading(query: String) : SearchUiState(isLoading = true, query = query)

    class Error(errorMsg: String, query: String) : SearchUiState(
        isError = true,
        errorMsg = errorMsg,
        query = query
    )

    class Retry(retryMsg: String, query: String) : SearchUiState(
        isRetry = true,
        retryMsg = retryMsg,
        query = query
    )

    class Search(val text: String) : SearchUiState(query = text, isLoading = true)

    class Empty(query: String = "") : SearchUiState(isEmpty = true, query = query)

    class Loaded(result: List<ExchangeRate>, query: String) :
        SearchUiState(result = result, query = query)
}