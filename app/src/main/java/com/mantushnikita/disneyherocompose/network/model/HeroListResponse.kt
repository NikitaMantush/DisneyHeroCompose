package com.mantushnikita.disneyherocompose.network.model

import com.mantushnikita.disneyherocompose.model.Hero
import com.mantushnikita.disneyherocompose.model.Info

data class HeroListResponse(
    val `data`: List<Hero>,
    val info: Info
)