package com.fired.home

import androidx.lifecycle.viewModelScope
import com.fired.core.base.BaseViewModel
import com.fired.core.base.UIEvent
import com.fired.core.base.UIState
import com.fired.core.ext.retryWithPolicy
import com.fired.home.util.Constant
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
) : BaseViewModel<HomeUiState, HomeUiEvent>(HomeUiState.Loading) {

    init {
        fetchRates()
    }

    private fun fetchRates() {
        exchangeRateInteractor
            .getLiveRates(Constant.liveRateFetchInterval)
            .retryWithPolicy { handleRetry() }
            .catch { e -> handleError(e) }
            .onEach {
                setState(HomeUiState.Loaded(rates = it))
            }.launchIn(viewModelScope)
    }

    private fun handleError(e: Throwable) {
        val errorMessage = e.message ?: "Error while fetching the exchange rates"
        setState(HomeUiState.Retry(retryMsg = errorMessage))
    }

    private fun handleRetry() {
        val retryMsg = "Loading data is failed"
        setState(HomeUiState.AutoRetry(retryMsg))
    }

    override fun onEvent(event: HomeUiEvent) {
        when (event) {
            HomeUiEvent.Retry -> {
                fetchRates()
                setState(HomeUiState.Loading)
            }
        }
    }
}

sealed class HomeUiState(
    val rates: List<ExchangeRate> = emptyList(),
    val isLoading: Boolean = false,
    val isRetry: Boolean = false,
    val retryMsg: String = "",
    val isAutoRetry: Boolean = false,
    val autoRetryMsg: String = "",
) : UIState {
    object Loading : HomeUiState(isLoading = true)

    class Retry(retryMsg: String) : HomeUiState(isRetry = true, retryMsg = retryMsg)

    class AutoRetry(autoRetryMsg: String) : HomeUiState(isAutoRetry = true, retryMsg = autoRetryMsg)

    class Loaded(rates: List<ExchangeRate>) : HomeUiState(rates = rates)
}

sealed interface HomeUiEvent : UIEvent {
    object Retry : HomeUiEvent
}