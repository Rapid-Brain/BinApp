package com.fired.search.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.fired.search.SearchScreen

/**
 * @author yaya (@yahyalmh)
 * @since 05th November 2022
 */


const val searchRoute = "search_route"

fun NavController.navigateToSearch(navOptions: NavOptions? = null) {
    this.navigate(searchRoute, navOptions)
}

fun NavGraphBuilder.searchGraph(navController: NavHostController) {
    composable(route = searchRoute) {
        SearchScreen(navController = navController)
    }
}