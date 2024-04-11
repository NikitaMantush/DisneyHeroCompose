package com.mantushnikita.disneyherocompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mantushnikita.disneyherocompose.ui.favourite.list.FavouriteListScreen
import com.mantushnikita.disneyherocompose.ui.hero.HeroScreen
import com.mantushnikita.disneyherocompose.ui.list.ListScreen
import com.mantushnikita.disneyherocompose.ui.login.LoginScreen
import com.mantushnikita.disneyherocompose.ui.singup.SingUpScreen

const val LIST_SCREEN = "ListScreen"
const val HERO_SCREEN = "HeroScreen"
const val LOGIN_SCREEN = "LoginScreen"
const val SINGUP_SCREEN = "SingUpScreen"
const val FAVOURITE_SCREEN = "FavouriteScreen"

@Composable
fun NavigationGraph(
    navigationController: NavHostController
) {
    NavHost(navController = navigationController, startDestination = LIST_SCREEN) {
        composable(LIST_SCREEN) {
            ListScreen(navigationController)
        }
        composable(FAVOURITE_SCREEN){
            FavouriteListScreen(navigationController)
        }
        composable("$HERO_SCREEN/{id}", arguments = listOf(navArgument("id") {
            type = NavType.IntType
        })) {
            HeroScreen(it.arguments?.getInt("id", 0) ?: 0)
        }
        composable(LOGIN_SCREEN){
            LoginScreen(navigationController)
        }
        composable(SINGUP_SCREEN){
            SingUpScreen(navigationController)
        }
    }
}