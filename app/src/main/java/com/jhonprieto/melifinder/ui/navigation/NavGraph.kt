package com.jhonprieto.melifinder.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jhonprieto.melifinder.ui.components.searchBarWithRecent
import com.jhonprieto.melifinder.ui.screens.home.homeScreen
import com.jhonprieto.melifinder.ui.screens.productDetail.productDetailScreen
import com.jhonprieto.melifinder.ui.screens.search.searchScreen

@Composable
fun navGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "home") {
        composable("home") {
            homeScreen(
                onSearchClick = {
                    navController.navigate("search")
                },
                onCategoryClick = { category ->
                    navController.navigate("search")
                }
            )
        }
        composable("search") {
            searchBarWithRecent(
                onCancel = { navController.popBackStack() },
                onSearch = { query ->
                    if (query.isNotBlank()) {
                        navController.navigate("search_screen/$query")
                    }
                }
            )
        }
        composable("search_screen/{query}") { backStackEntry ->
            val query = backStackEntry.arguments?.getString("query") ?: ""
            searchScreen(
                query = query,
                onProductClick = { productId ->
                    navController.navigate("product_detail/$productId")
                }
            )
        }
        composable("product_detail/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            productDetailScreen(productId = productId) {
                navController.popBackStack() // O lo que quieras para volver
            }
        }
        composable("results") { /* ResultsScreen() */ }
        composable("detail/{productId}") { backStackEntry ->
            // val productId = backStackEntry.arguments?.getString("productId")
            /* DetailScreen(productId) */
        }
    }
}
