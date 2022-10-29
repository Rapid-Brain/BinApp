package com.fired.binapp.ui

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fired.binapp.R
import com.fired.binapp.navigation.AppNavigation
import com.fired.network.NetworkMonitor

/**
 * @author yaya (@yahyalmh)
 * @since 29th October 2022
 */
@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun MainScreen(
    networkMonitor: NetworkMonitor,
    appState: AppState = rememberAppState(
        networkMonitor = networkMonitor,
    )
) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        contentColor = MaterialTheme.colors.onBackground,
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { padding ->
        CheckNetworkConnection(appState, snackbarHostState)
        SetupAppNavigation(appState, padding)
    }
}

@Composable
@OptIn(ExperimentalLayoutApi::class)
private fun SetupAppNavigation(
    appState: AppState,
    padding: PaddingValues
) {
//    AppNavigation(
//        navController = appState.navController,
//        modifier = Modifier
//            .padding(padding)
//            .consumedWindowInsets(padding)
//    )
}

@Composable
@OptIn(ExperimentalLifecycleComposeApi::class)
private fun CheckNetworkConnection(
    appState: AppState,
    snackbarHostState: SnackbarHostState
) {
    val isOffline by appState.isOffline.collectAsStateWithLifecycle()

    val notConnected = stringResource(R.string.not_connected)
    LaunchedEffect(isOffline) { if (isOffline) showSnackbar(snackbarHostState, notConnected) }
}

private suspend fun showSnackbar(
    snackbarHostState: SnackbarHostState,
    message: String
) {
    snackbarHostState.showSnackbar(
        message = message,
        duration = SnackbarDuration.Short
    )
}