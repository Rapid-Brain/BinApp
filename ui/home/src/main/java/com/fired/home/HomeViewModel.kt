package com.fired.home

import androidx.lifecycle.viewModelScope
import com.fired.core.base.BaseViewModel
import com.fired.core.base.UIEvent
import com.fired.core.base.UIState
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
    private val exchangeRateInteractor: ExchangeRateInteractor
) : BaseViewModel<HomeUiState, HomeUiEvent>(initialStat = HomeUiState.initState) {

    init {
        fetchRates()
    }

    private fun fetchRates() {
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
            HomeUiEvent.Retry -> {
                fetchRates()
                setState(
                    state.value.copy(
                        isLoading = true,
                        isError = false,
                    )
                )
            }
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