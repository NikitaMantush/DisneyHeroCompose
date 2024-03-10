package com.mantushnikita.disneyherocompose.ui.hero

sealed class HeroAction {
    class LoadById(val heroId: Int) : HeroAction()
    object Reload : HeroAction()

}