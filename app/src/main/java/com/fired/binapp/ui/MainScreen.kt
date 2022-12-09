package com.fired.binapp.ui

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fired.binapp.nav.AppNavigation
import com.fired.core.network.NetworkMonitor

/**
 * @author yaya (@yahyalmh)
 * @since 29th October 2022
 */
@Composable
fun MainScreen(
    networkMonitor: NetworkMonitor,
//    appState: AppState = rememberAppState(
//        networkMonitor = networkMonitor,
//    ),
    viewModel: MainViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
//    val navController: NavHostControlleler = rememberNavController()

    val snackbarHostState = remember { SnackbarHostState() }
    Column {
        OutlinedButton(onClick = {viewModel.onEvent(MainUiEvent.ChangeThem(23)) }) {
            Text(text = "Theme")
        }
        NetStatusView(state.showOnlineView, state.showOfflineView)

//        ObserveNetworkConnection(appState)

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
        SetupAppNavigation(viewModel.navController)

    }
}

@Composable
@OptIn(ExperimentalLayoutApi::class)
private fun SetupAppNavigation(
    navHostController: NavHostController,
    padding:PaddingValues= PaddingValues.Absolute()
) {
    AppNavigation(
        navController = navHostController,
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

//    NetStatusView(appState, isOffline)
}
