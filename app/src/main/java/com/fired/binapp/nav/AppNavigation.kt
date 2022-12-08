package com.fired.binapp.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.client.search.nav.searchGraph
import com.client.search.nav.searchRoute
import com.fired.detail.nav.detailGraph
import com.fired.home.nav.homeGraph
import com.fired.home.nav.homeRoute

@Composable
fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = searchRoute
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        homeGraph(navController)
        detailGraph()
        searchGraph(navController)
    }
}