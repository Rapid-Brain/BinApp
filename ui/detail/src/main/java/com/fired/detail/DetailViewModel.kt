package com.fired.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.fired.core.base.BaseViewModel
import com.fired.core.base.UIEvent
import com.fired.core.base.UIState
import com.fired.detail.nav.DetailArgs
import com.fired.rate.interactor.ExchangeDetailRate
import com.fired.rate.interactor.ExchangeRateInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * @author yaya (@yahyalmh)
 * @since 10th November 2022
 */

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val exchangeRateInteractor: ExchangeRateInteractor,
) : BaseViewModel<DetailUiState, DetailUiEvent>(DetailUiState.Loading) {
    private val detailArgs: DetailArgs = DetailArgs(savedStateHandle)

    init {
        fetchRate()
    }

    private fun fetchRate() {
        exchangeRateInteractor.getLiveRate(detailArgs.rateId)
            .catch { e ->
                val errorMessage = e.message ?: "Error while fetching the exchange rate"
                setState(DetailUiState.Error(errorMessage))
            }.onEach {
                setState(DetailUiState.Loaded(rate = it))
            }.launchIn(viewModelScope)
    }

    override fun onEvent(event: DetailUiEvent) {
        when (event) {
            DetailUiEvent.Retry -> {
                fetchRate()
                setState(DetailUiState.Loading)
            }
        }
    }
}

sealed interface DetailUiEvent : UIEvent {
    object Retry : DetailUiEvent
}

sealed class DetailUiState(
    val rate: ExchangeDetailRate?,
    val isLoading: Boolean,
    val isError: Boolean,
    val errorMsg: String,
) : UIState {
    object Loading : DetailUiState(
        rate = null,
        isLoading = true,
        isError = false,
        errorMsg = ""
    )

    class Error(errorMsg: String) : DetailUiState(
        rate = null,
        isLoading = false,
        isError = true,
        errorMsg = errorMsg
    )

    class Loaded(rate: ExchangeDetailRate) : DetailUiState(
        rate = rate,
        isLoading = false,
        isError = false,
        errorMsg = ""
    )
}