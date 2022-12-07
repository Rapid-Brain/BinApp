package com.fired.binapp.ui

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fired.binapp.R
import com.fired.binapp.nav.AppNavigation
import com.fired.core.component.AppBar
import com.fired.core.network.NetworkMonitor

/**
 * @author yaya (@yahyalmh)
 * @since 29th October 2022
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    networkMonitor: NetworkMonitor,
    appState: AppState = rememberAppState(
        networkMonitor = networkMonitor,
    )
) {
    val snackbarHostState = remember { SnackbarHostState() }
    Column {
        ObserveNetworkConnection(appState)

//        Scaffold(
//            contentColor = MaterialTheme.colors.onBackground,
//            snackbarHost = { SnackbarHost(snackbarHostState) },
//            topBar = {
//                AppBar(
//                    titleRes = R.string.app_name,
//                    modifier = Modifier.zIndex(-1F),
//                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
//                        containerColor = Color.Transparent
//                    ),
//                    navigationIcon = Icons.Default.Menu,
//                    navigationIconContentDescription = "Menu",
//                    actionIcon = Icons.Default.Search,
//                    actionIconContentDescription = "Search",
//                    onNavigationClick = {}
//                )
//            }
//        ) { padding ->
//            SetupAppNavigation(appState, padding)
//        }
        SetupAppNavigation(appState)

    }
}

@Composable
@OptIn(ExperimentalLayoutApi::class)
private fun SetupAppNavigation(
    appState: AppState,
    padding:PaddingValues= PaddingValues.Absolute()
) {
    AppNavigation(
        navController = appState.navController,
        modifier = Modifier
            .padding(padding)
            .consumedWindowInsets(padding)
    )
}

@Composable
@OptIn(ExperimentalLifecycleComposeApi::class)
private fun ObserveNetworkConnection(appState: AppState) {
    val isOffline by appState.isOffline.collectAsStateWithLifecycle()

    appState.isOnlineViewVisible = isOffline.not()

    NetStatusView(appState, isOffline)
}
