package com.fired.binapp.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.fired.detail.nav.detailGraph
import com.fired.home.nav.homeGraph
import com.fired.home.nav.homeRoute
import com.fired.search.nav.searchGraph

/**
 * @author yaya (@yahyalmh)
 * @since 29th October 2022
 */
@Composable
fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = homeRoute
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        homeGraph(navController)
        detailGraph(navController)
        searchGraph(navController)
    }
}