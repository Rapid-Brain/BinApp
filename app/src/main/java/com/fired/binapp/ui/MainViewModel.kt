package com.fired.binapp.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fired.core.base.BaseViewModel
import com.fired.core.base.UIEvent
import com.fired.core.base.UIState
import com.fired.core.network.NetworkMonitor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    networkMonitor: NetworkMonitor
) : BaseViewModel<MainUiState, MainUiEvent>(MainUiState.None) {
    private var isFirstTime: Boolean = true

    val navController: NavHostController
        @Composable
        get() = rememberNavController()

    init {
        networkMonitor.isOnline
            .distinctUntilChanged()
            .onEach { isOnline ->
                if (isOnline) {
                    handelOnlineState()
                } else {
                    setState(MainUiState.Offline)
                }
            }.launchIn(viewModelScope)
    }

    private fun handelOnlineState() {
        if (isFirstTime) {
            isFirstTime = false
            return
        }
        setState(MainUiState.Online)
        hideOnlineViewAfterWhile()
    }

    private fun hideOnlineViewAfterWhile() {
        val hideOnlineViewDelay: Long = 2000
        viewModelScope.launch {
            delay(hideOnlineViewDelay)
            setState(MainUiState.None)
        }
    }

    override fun onEvent(event: MainUiEvent) {
        when (event) {
            is MainUiEvent.ChangeThem -> {
                setState(
                    MainUiState.ChangeTheme(
                        state.value.showOnlineView,
                        state.value.showOfflineView
                    )
                )
            }
        }
    }
}

sealed interface MainUiEvent : UIEvent {
    class ChangeThem(val state: Int) : MainUiEvent
}

sealed class MainUiState(
    val showOnlineView: Boolean,
    val showOfflineView: Boolean,
    val darkThemeConfig: DarkThemeConfig = DarkThemeConfig.FOLLOW_SYSTEM,
) : UIState {

    object None : MainUiState(false, false)

    object Offline : MainUiState(
        showOnlineView = false,
        showOfflineView = true,
    )

    object Online : MainUiState(
        showOnlineView = true,
        showOfflineView = false,
    )

    class ChangeTheme(
        showOnlineView: Boolean,
        showOfflineView: Boolean
    ) : MainUiState(
        showOnlineView = showOnlineView,
        showOfflineView = showOfflineView,
        darkThemeConfig = DarkThemeConfig.DARK
    )
}

enum class DarkThemeConfig {
    FOLLOW_SYSTEM, LIGHT, DARK
}