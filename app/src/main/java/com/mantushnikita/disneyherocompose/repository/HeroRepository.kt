package com.mantushnikita.disneyherocompose.repository

import com.mantushnikita.disneyherocompose.network.Api
import javax.inject.Inject

class HeroRepository @Inject constructor(
    private val api: Api
) {
    suspend fun getHeroes() = api.getHeroes()

    suspend fun getHeroById(id: Int) = api.getHeroById(id)
}