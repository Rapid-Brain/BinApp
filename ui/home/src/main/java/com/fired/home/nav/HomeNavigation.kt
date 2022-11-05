package com.fired.home.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.fired.home.HomeScreen

/**
 * @author yaya (@yahyalmh)
 * @since 05th November 2022
 */


const val homeRoute = "home_route"

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(homeRoute, navOptions)
}

fun NavGraphBuilder.homeGraph() {
    composable(route = homeRoute) {
        HomeScreen()
    }
}