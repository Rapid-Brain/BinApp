package com.fired.home

import androidx.lifecycle.viewModelScope
import com.fired.core2.base.BaseViewModel
import com.fired.core2.base.UIEvent
import com.fired.core2.base.UIState
import com.fired.rate.interactor.ExchangeRate
import com.fired.rate.interactor.ExchangeRateInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * @author yaya (@yahyalmh)
 * @since 05th November 2022
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    exchangeRateInteractor: ExchangeRateInteractor
) : BaseViewModel<HomeUiState, HomeUiEvent>(initialStat = HomeUiState.initState) {

    init {
        val liveRateFetchInterval = 3000L
        exchangeRateInteractor
            .getLiveRates(liveRateFetchInterval)
            .catch { e ->
                val errorMessage = e.message ?: "Error while fetching the exchange rates"
                setState(
                    state.value.copy(
                        isLoading = false,
                        isError = true,
                        errorMessage = errorMessage
                    )
                )
            }.onEach {
                setState(state.value.copy(rates = it, isError = false, isLoading = false))
            }.launchIn(viewModelScope)
    }

    override fun onEvent(event: HomeUiEvent) {
        when (event) {
            HomeUiEvent.Retry -> println("RETRY")
        }
    }
}

data class HomeUiState(
    val rates: List<ExchangeRate>,
    val isLoading: Boolean,
    val isError: Boolean,
    val errorMessage: String,
) : UIState {
    companion object {
        val initState = HomeUiState(
            rates = emptyList(),
            isLoading = true,
            isError = false,
            errorMessage = ""
        )
    }
}

sealed interface HomeUiEvent : UIEvent {
    object Retry : HomeUiEvent
}