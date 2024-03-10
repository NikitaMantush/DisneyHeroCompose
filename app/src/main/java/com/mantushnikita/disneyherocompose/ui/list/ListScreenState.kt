package com.mantushnikita.disneyherocompose.ui.list

import com.mantushnikita.disneyherocompose.model.Hero

sealed class ListScreenState {
    object Loading: ListScreenState()
    class Error(val message:String):ListScreenState()
    class Loaded(val heroList:List<Hero>): ListScreenState()
}