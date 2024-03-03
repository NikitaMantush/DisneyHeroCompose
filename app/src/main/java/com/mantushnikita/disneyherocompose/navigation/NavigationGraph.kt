package com.mantushnikita.disneyherocompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mantushnikita.disneyherocompose.ui.hero.HeroScreen
import com.mantushnikita.disneyherocompose.ui.list.ListScreen

const val LIST_SCREEN = "ListScreen"
const val HERO_SCREEN = "HeroScreen"

@Composable
fun NavigationGraph(
    navigationController: NavHostController
) {
    NavHost(navController = navigationController, startDestination = LIST_SCREEN) {
        composable(LIST_SCREEN) {
            ListScreen(navigationController)
        }
        composable("$HERO_SCREEN/{id}", arguments = listOf(navArgument("id") {
            type = NavType.IntType
        })) {
            HeroScreen(it.arguments?.getInt("id", 0) ?: 0)
        }
    }
}