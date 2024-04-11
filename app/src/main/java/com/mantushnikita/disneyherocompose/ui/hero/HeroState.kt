package com.mantushnikita.disneyherocompose.ui.hero

import com.mantushnikita.disneyherocompose.model.Hero

sealed class HeroState {
    object Loading : HeroState()
    class Error(val error: String) : HeroState()
    class HeroLoaded(val hero: Hero): HeroState()
}