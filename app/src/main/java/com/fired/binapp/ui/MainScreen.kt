package com.fired.binapp.ui

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.fired.binapp.nav.AppNavigation

/**
 * @author yaya (@yahyalmh)
 * @since 29th October 2022
 */
@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
    val uiState = viewModel.state.value

//    val navController: NavHostController = rememberNavController()
//    val snackbarHostState = remember { SnackbarHostState() }

    Column {
        NetStatusView(uiState.showOnlineView, uiState.showOfflineView)
        SetupAppNavigation(viewModel.navController)

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
    }
}

@Composable
@OptIn(ExperimentalLayoutApi::class)
private fun SetupAppNavigation(
    navHostController: NavHostController,
    padding: PaddingValues = PaddingValues.Absolute()
) {
    AppNavigation(
        navController = navHostController,
        modifier = Modifier
            .padding(padding)
            .consumedWindowInsets(padding)
    )
}

