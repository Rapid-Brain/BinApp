package com.client.search

import androidx.compose.runtime.mutableStateOf
import com.fired.core.base.BaseViewModel
import com.fired.core.base.UIEvent
import com.fired.core.base.UIState
import com.fired.rate.interactor.ExchangeRate
import com.fired.rate.interactor.ExchangeRateInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author yaya (@yahyalmh)
 * @since 05th November 2022
 */

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val exchangeRateInteractor: ExchangeRateInteractor
) : BaseViewModel<SearchUiState, SearchUiEvent>(SearchUiState.initState) {

    val query = mutableStateOf("")
    override fun onEvent(event: SearchUiEvent) {
        when (event) {
            SearchUiEvent.Search -> {
                // do something
            }
        }
    }
}

data class SearchUiState(
    val rates: List<ExchangeRate> = emptyList(),
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val errorMessage: String = ""
) : UIState {
    companion object {
        val initState = SearchUiState(
            rates = emptyList(),
            isLoading = true,
            isError = false,
            errorMessage = ""
        )
    }
}

sealed interface SearchUiEvent : UIEvent {
    object Search : SearchUiEvent
}