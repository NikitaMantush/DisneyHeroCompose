package com.mantushnikita.disneyherocompose.ui.favourite.list

import com.mantushnikita.disneyherocompose.model.Hero

sealed class FavouriteListState {
    object Loading : FavouriteListState()
    class Error(val error: String): FavouriteListState()
    class Loaded(val heroList:List<Hero>): FavouriteListState()
}